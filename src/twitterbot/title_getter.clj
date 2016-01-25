(ns twitterbot.title-getter
    (:require [clj-http.client :as client]))

(defn get-title
  [url]
  (nth (re-find #"<title>(.*)</title>" ((client/get url) :body)) 1))
