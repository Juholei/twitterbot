(ns twitterbot.title-getter
    (:require [clj-http.client :as client]))

(defn retrieve-webpage [url]
  ((client/get url) :body))

(defn get-title-from-webpage [html]
  (nth (re-find #"<title>\s*(.*)\s*</title>" html) 1))
