(ns twitterbot.core
  (:gen-class)
  (:require [twitterbot.my-twitter :refer :all]
            [twitterbot.tweet-backlog :as backlog]
            [twitterbot.title-getter :refer [get-title]]))

(defn -main
  [& args]
  (let [links-to-add (links-from-direct-messages (get-direct-messages))]
    (doseq [link links-to-add]
           (backlog/push-a-tweet "things_to_tweet.txt" link)))
  (let [url (backlog/pop-a-tweet "things_to_tweet.txt")]
    (tweet (get-title url) url)
    (dm-trusted-user (str "Just tweeted " url))))
