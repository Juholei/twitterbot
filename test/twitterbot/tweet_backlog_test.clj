(ns twitterbot.tweet-backlog-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :refer [delete-file]]
            [twitterbot.tweet-backlog :refer :all]))

(def ^:private test-file "test-file.txt")

(defn test-fixture [f]
  (f)
  (delete-file test-file))

(use-fixtures :each test-fixture)

(deftest pushing-and-popping
    (testing "Pushing a link to an empty file and popping returns the same"
      (push-a-tweet test-file "https://daringfireball.net/")
      (is (= "https://daringfireball.net/" (pop-a-tweet test-file)))))

(deftest pushing-pushing-popping-pushing
  (testing "jee"
    (push-a-tweet test-file "https://daringfireball.net/")
    (push-a-tweet test-file "https://marco.org/")
    ; (pop-a-tweet test-file)
    (is (= "https://daringfireball.net/" (pop-a-tweet test-file)))
    (push-a-tweet test-file "http://www.merlinmann.com/roderick/")
    (is (= "https://marco.org/\nhttp://www.merlinmann.com/roderick/\n"
            (slurp test-file)))))
