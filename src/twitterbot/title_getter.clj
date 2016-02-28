(ns twitterbot.title-getter
    (:require [clojure.edn :as edn]
              [clojure.string :as string]
              [clj-http.client :as client]))

(def ^:private html-character-map (edn/read-string (slurp "resources/html_characters.edn")))

(defn retrieve-webpage [url]
  ((client/get url) :body))

(defn get-title-from-webpage [html]
  (nth (re-find #"<title>\s*(.*)\s*</title>" html) 1))

(defn replace-html-characters [text]
  (string/join " " (replace html-character-map (string/split text #" "))))
