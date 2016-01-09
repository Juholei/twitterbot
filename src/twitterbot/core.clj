(ns twitterbot.core
  (:gen-class)
  (:require [twitterbot.my-twitter :refer [tweet]]
            [twitterbot.tweet-backlog :as backlog]))

(defn -main
  [& args]
  (tweet (backlog/pop-a-tweet "things_to_tweet.txt")))