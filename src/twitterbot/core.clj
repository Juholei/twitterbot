(ns twitterbot.core
  (:gen-class)
  (:require [twitterbot.my-twitter :refer :all]
            [twitterbot.tweet-backlog :as backlog]
            [twitterbot.title-getter :refer [retrieve-webpage get-title-from-webpage replace-html-characters]]))

(defn- get-links-from-direct-messages []
  (let [dm-response (get-direct-messages (backlog/newest-dm-read "newest_dm_id.txt"))]
    (backlog/update-newest-dm-read "newest_dm_id.txt" (get-in dm-response [:body 0 :id_str]))
    (let [links-to-add (links-from-direct-messages dm-response)]
      (doseq [link links-to-add]
             (backlog/push-a-tweet "things_to_tweet.txt" link)
             (dm-trusted-user (str "Added " link))))))

(defn- tweet-from-backlog []
  (let [url (backlog/pop-a-tweet "things_to_tweet.txt")]
    (-> url
        retrieve-webpage
        get-title-from-webpage
        replace-html-characters
        (tweet url))
    (dm-trusted-user (str "Just tweeted " url))))

(defn -main [& args]
  (get-links-from-direct-messages)
  (tweet-from-backlog))
