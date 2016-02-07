(ns twitterbot.my-twitter
    (:require [twitter.oauth :refer [make-oauth-creds]]
              [twitter.api.restful :refer [statuses-update direct-messages direct-messages-new]]
              [clojure.edn :as edn]
              [clojure.string :as string]))

(defn- load-credentials [filename]
  (edn/read-string (slurp filename)))

(def ^:private credentials-from-file (load-credentials "credentials.edn"))

(def ^:private oauth-credentials
  (make-oauth-creds (credentials-from-file :consumerkey)
                    (credentials-from-file :consumersecret)
                    (credentials-from-file :usertoken)
                    (credentials-from-file :usersecret)))

(defn tweet [title url]
  (let [to-be-tweeted (string/join " " [title url])]
    (statuses-update :oauth-creds oauth-credentials
                     :params {:status to-be-tweeted})))

(defn dm-trusted-user [message]
  (direct-messages-new :oauth-creds oauth-credentials
                       :params {:screen-name (credentials-from-file :trusteduser)
                                :text message}))
