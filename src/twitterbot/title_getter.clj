(ns twitterbot.title-getter
  (:require [clojure.edn :as edn]
            [clojure.string :as string]
            [clojure.java.io :as io]
            [clj-http.client :as client]))

(def ^:private html-character-map (edn/read-string (slurp (io/resource "html_characters.edn"))))

(defn retrieve-webpage [url]
  ((client/get url) :body))

(defn get-title-from-webpage [html]
  (nth (re-find #"<title>\s*(.*)\s*</title>" html) 1))

(defn- replace-html-character [text characters]
  (if (empty? characters)
    text
    (recur (string/replace text (first characters) (html-character-map (first characters))) (rest characters))))

(defn replace-html-characters [text]
  (replace-html-character text (keys html-character-map)))
