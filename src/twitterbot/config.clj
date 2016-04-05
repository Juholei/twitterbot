(ns twitterbot.config
    (:require [clojure.edn :as edn]))

(defn- load-config [filename]
  (edn/read-string (slurp filename)))

(def config (load-config "config.edn"))
