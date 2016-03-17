(ns twitterbot.tweet-backlog-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :refer [delete-file]]
            [twitterbot.tweet-backlog :refer :all]))

(def ^:private test-file "test-file.txt")

(defn test-fixture [f]
  (f)
  (delete-file test-file true))

(use-fixtures :each test-fixture)

(deftest pushing-and-popping
    (testing "Pushing a link to an empty file and popping returns the same"
      (push-a-tweet test-file "https://daringfireball.net/")
      (is (= "https://daringfireball.net/" (pop-a-tweet test-file)))))

(deftest pushing-pushing-popping-pushing
  (testing "Putting two links and getting one out and then adding a new one results in the second and third link being in the file"
    (push-a-tweet test-file "https://daringfireball.net/")
    (push-a-tweet test-file "https://marco.org/")
    (is (= "https://daringfireball.net/" (pop-a-tweet test-file)))
    (push-a-tweet test-file "http://www.merlinmann.com/roderick/")
    (is (= "https://marco.org/\nhttp://www.merlinmann.com/roderick/\n"
            (slurp test-file)))))

(deftest updating-newest-dm-read-to-file-and-then-reading-it
  (testing "Updating the newest dm id to a file and then reading it from there"
    (let [dm-id "4723101797"]
      (update-newest-dm-read test-file dm-id)
      (is (= dm-id (newest-dm-read test-file))))))

(deftest reading-newest-dm-id-when-it-does-not-exist-return-nil
  (testing "Reading newest dm id from file should return nil when the file doesn't exist"
      (is (= nil (newest-dm-read test-file)))))

(deftest saving-newest-dm-should-not-accept-nil
  (testing "Previous value should be kept even after update-newest-dm-read-is called with nil value"
    (let [dm-id "4723101797"]
      (update-newest-dm-read test-file dm-id)
      (update-newest-dm-read test-file nil)
      (is (= dm-id (newest-dm-read test-file))))))
