(ns twitterbot.core
  (:gen-class)
  (:require [twitterbot.my-twitter :refer :all]
            [twitterbot.tweet-backlog :as backlog]
            [twitterbot.title-getter :refer [get-title]]))

(defn -main
  [& args]
  (backlog/push-a-tweet "things_to_tweet.txt" (links-from-direct-messages (get-direct-messages)))
  (let [url (backlog/pop-a-tweet "things_to_tweet.txt")]
    (tweet (get-title url) url)))