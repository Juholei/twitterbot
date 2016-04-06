(ns twitterbot.my-twitter
  (:require [twitter.oauth :refer [make-oauth-creds]]
            [twitter.api.restful :refer [statuses-update direct-messages
                                         direct-messages-new help-configuration]]
            [clojure.string :as string]
            [twitterbot.config :refer [config]]))

(def ^:private max-link-length 23)
(def ^:private max-tweet-length 140)

(def ^:private oauth-credentials)

(defn create-oauth-credentials []
  (alter-var-root #'oauth-credentials
                  (constantly (make-oauth-creds (config :consumerkey)
                                                (config :consumersecret)
                                                (config :usertoken)
                                                (config :usersecret)))))

(defn tweet [title url]
  (let [to-be-tweeted (string/join " " [title url])]
    (statuses-update :oauth-creds oauth-credentials
                     :params {:status to-be-tweeted})))

(defn- has-url? [message]
  (seq (:urls (:entities message))))

(defn- trusted-user? [message]
  (= (config :trusteduser) (:sender_screen_name message)))

(defn- get-url [message]
  (get-in message [:entities :urls 0 :expanded_url]))

(defn get-direct-messages [dm-id]
  (if dm-id
    (direct-messages :oauth-creds oauth-credentials
                     :params {:since_id dm-id})
    (direct-messages :oauth-creds oauth-credentials)))

(defn links-from-direct-messages [twitter-dm-response]
  (->> twitter-dm-response
       :body
       (filter trusted-user?)
       (filter has-url?)
       (map get-url)))

(defn dm-trusted-user [message]
  (direct-messages-new :oauth-creds oauth-credentials
                       :params {:screen-name (config :trusteduser)
                                :text message}))

(defn too-long-tweet? [string]
  (> (+ (count string) max-link-length) max-tweet-length))

(defn add-hashtags [page-title hashtags]
  (if (empty? hashtags)
    page-title
    (let [new-tweet (string/join " " [page-title (first hashtags)])]
      (when ((complement too-long-tweet?) new-tweet)
        (recur new-tweet (rest hashtags))))))
