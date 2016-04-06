(ns twitterbot.config
    (:require [clojure.edn :as edn]))

(def config {:consumerkey ""
             :consumersecret ""
             :usertoken ""
             :usersecret ""
             :trusteduser ""
             :hashtags []})

(defn load-config [filename]
  (when-let [config-from-file (edn/read-string (slurp filename))]
    (alter-var-root #'config (constantly config-from-file))))

