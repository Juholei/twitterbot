(ns twitterbot.mytwitter
    (:use
        [twitter.oauth]
        [twitter.api.restful])
    (:require [clojure.edn :as edn]))

(defn load-credentials
    [filename]
    (edn/read-string (slurp filename)))

(def credentials-from-file (load-credentials "credentials.edn"))

(def oauth-credentials (make-oauth-creds (credentials-from-file :consumerkey)
                                (credentials-from-file :consumersecret)
                                (credentials-from-file :usertoken)
                                (credentials-from-file :usersecret)))

(defn tweet
    [string]
    (statuses-update :oauth-creds oauth-credentials
                     :params {:status string})
    )
