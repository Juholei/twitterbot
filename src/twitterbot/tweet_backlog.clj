(ns twitterbot.tweet-backlog
  (:require [clojure.string :as string]))

(defn pop-a-tweet [file]
  (let [tweets (string/split-lines (slurp file))]
    (spit file (str (string/join "\n" (rest tweets)) "\n"))
    (first tweets)))

(defn push-a-tweet [file new-link]
  (spit file (str new-link "\n") :append true))
