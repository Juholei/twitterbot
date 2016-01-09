(ns twitterbot.tweet-backlog
  (:require [clojure.string :as string]))

(defn pop-a-tweet
  [file]
  (let [tweets (string/split-lines (slurp file))]
  (spit file (string/join "\n" (rest tweets)))
  (first tweets)))