(ns twitterbot.my-twitter-test
  (:require [clojure.test :refer :all]
            [clojure.edn :as edn]
            [twitterbot.my-twitter :refer :all]
            [twitterbot.config :refer [config]]))

;This test requires that trusted user has been set in credentials.edn file
(deftest links-from-direct-messages-returns-correct-link
  (let [twitter-dm-response {:body [{:entities {:urls [{:url "https://daringfireball.net/", :expanded_url "https://daringfireball.net/"}]} :sender_screen_name (config :trusteduser)}]}]
    (testing "links-from-direct-messages returns the correct link"
      (is (= '("https://daringfireball.net/") (links-from-direct-messages twitter-dm-response))))))

(deftest empty-string-is-not-a-too-long-tweet
  (testing "Empty string is not too long to be tweeted"
    (is (= false (too-long-tweet? "")))))

(deftest tweet-of-141-characters-is-too-long
  (testing "Too long tweet of 141 characters is recognized to be too long"
    (is (= true (too-long-tweet? "Hello. This is a tweet. Tweeting is really nice. I sometimes wonder how many things I have to say. Now I can tell you all my secrets. First I")))))

(deftest add-hashtags-to-tweet
  (testing "Add hashtags to tweet when the result is not over 140 characters"
    (is (= "Hacker News #programming #coding" (add-hashtags "Hacker News" ["#programming" "#coding"])))))

(deftest add-hashtags-when-there-are-none
  (testing "add-hashtags is called without any hashtags given and results in nothing being added"
    (is (= "Hacker News" (add-hashtags "Hacker News" [])))))
