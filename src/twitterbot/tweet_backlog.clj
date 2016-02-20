(ns twitterbot.tweet-backlog
  (:require [clojure.string :as string])
  (:require [clojure.java.io :as io]))

(defn pop-a-tweet [filename]
  (let [tweets (string/split-lines (slurp filename))]
    (spit filename (str (string/join "\n" (rest tweets)) "\n"))
    (first tweets)))

(defn push-a-tweet [filename new-link]
  (spit filename (str new-link "\n") :append true))

(defn update-newest-dm-read [filename dm-id]
  (when dm-id
    (spit filename dm-id)))

(defn newest-dm-read [filename]
  (when (.exists (io/as-file filename))
    (slurp filename)))
