(ns twitterbot.my-twitter-test
  (:require [clojure.test :refer :all]
            [clojure.edn :as edn]
            [twitterbot.my-twitter :refer :all]))

(defn- load-credentials [filename]
  (edn/read-string (slurp filename)))

(def ^:private credentials-from-file (load-credentials "credentials.edn"))

;This test requires that trusted user has been set in credentials.edn file
(deftest links-from-direct-messages-returns-correct-link
  (let [twitter-dm-response {:body [{:entities {:urls [{:url "https://daringfireball.net/", :expanded_url "https://daringfireball.net/"}]} :sender_screen_name (credentials-from-file :trusteduser)}]}]
    (testing "links-from-direct-messages returns the correct link"
      (is (= '("https://daringfireball.net/") (links-from-direct-messages twitter-dm-response))))))
  
