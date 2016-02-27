(ns twitterbot.title-getter-test
  (:require [clojure.test :refer :all]
            [twitterbot.title-getter :refer :all]))

(deftest getting-title-from-html
    (testing ""
      (is (= "Daring Fireball" (get-title-from-webpage "<title>Daring Fireball</title>")))))

(deftest getting-title-from-html-when-title-tag-is-spans-multiple-lines-with-whitespace
    (testing ""
      (is (= "Daring Fireball" (get-title-from-webpage "<title>\n      Daring Fireball\n</title>")))))

(deftest when-title-contains-html-ampersand-it-is-replaced-by-correct-character
  (testing ""
      (is (= "Great title with & and stuff" (replace-html-characters "Great title with &amp; and stuff")))))
