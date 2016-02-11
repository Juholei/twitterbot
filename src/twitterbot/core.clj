(ns twitterbot.core
  (:gen-class)
  (:require [twitterbot.my-twitter :refer [tweet]]
            [twitterbot.tweet-backlog :as backlog]
            [twitterbot.title-getter :refer [get-title]]))

(defn -main
  [& args]
  (let [url (backlog/pop-a-tweet "things_to_tweet.txt")]
    (tweet (get-title url) url)))