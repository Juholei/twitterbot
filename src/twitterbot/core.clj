(ns twitterbot.core
  (:gen-class)
  (:require [twitterbot.mytwitter :as twit]))

(defn -main
  [& args]
  (twit/tweet "WHAT IS HAPPENING"))
