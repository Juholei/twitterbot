(ns twitterbot.core
  (:gen-class)
  (:require [twitterbot.mytwitter :refer [tweet]]))

(defn -main
  [& args]
  (tweet ":-D"))
