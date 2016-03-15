(ns twitterbot.config
    (:require [clojure.edn :as edn]))

(defn- load-config [filename]
  (edn/read-string (slurp filename)))

(def credentials-from-file (load-config "config.edn"))
