(ns twitterbot.my-twitter-test
  (:require [clojure.test :refer :all]
            [clojure.edn :as edn]
            [twitterbot.my-twitter :refer :all]))

(defn- load-credentials [filename]
  (edn/read-string (slurp filename)))

(def ^:private credentials-from-file (load-credentials "config.edn"))

;This test requires that trusted user has been set in credentials.edn file
(deftest links-from-direct-messages-returns-correct-link
  (let [twitter-dm-response {:body [{:entities {:urls [{:url "https://daringfireball.net/", :expanded_url "https://daringfireball.net/"}]} :sender_screen_name (credentials-from-file :trusteduser)}]}]
    (testing "links-from-direct-messages returns the correct link"
      (is (= '("https://daringfireball.net/") (links-from-direct-messages twitter-dm-response))))))

(deftest empty-string-is-not-a-too-long-tweet
  (testing "Empty string is not too long to be tweeted"
    (is (= true (too-long-tweet? "")))))

(deftest tweet-of-141-characters-is-too-long
  (testing "Too long tweet of 141 characters is recognized to be too long"
    (is (= false (too-long-tweet? "Hello. This is a tweet. Tweeting is really nice. I sometimes wonder how many things I have to say. Now I can tell you all my secrets. First I")))))
