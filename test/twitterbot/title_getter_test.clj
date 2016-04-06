(ns twitterbot.title-getter-test
  (:require [clojure.test :refer :all]
            [twitterbot.title-getter :refer :all]))

(deftest getting-title-from-html
  (testing "Get title from html when the tags are on same line"
    (is (= "Daring Fireball" (get-title-from-webpage "<title>Daring Fireball</title>")))))

(deftest getting-title-from-html-when-title-tag-is-spans-multiple-lines-with-whitespace
  (testing "Get title from html when the tags are on different lines"
    (is (= "Daring Fireball" (get-title-from-webpage "<title>\n      Daring Fireball\n</title>")))))

(deftest when-title-contains-html-ampersand-it-is-replaced-by-correct-character
  (testing "replacing html special character markup with correct character"
    (is (= "Great title with & and stuff" (replace-html-characters "Great title with &amp; and stuff")))))

(deftest when-title-contains-html-middot-it-is-replaced-by-correct-character
  (testing "replacing html special character markup with correct character"
    (is (= "Eight Terminal Utilities Every OS X Command Line User Should Know • mitchchn.me" (replace-html-characters "Eight Terminal Utilities Every OS X Command Line User Should Know &middot; mitchchn.me")))))

(deftest when-title-contains-html-mdash-it-is-replaced-by-correct-character
  (testing "replacing html special character markup with correct character"
    (is (= "literally words — Graphing when your Facebook friends are awake" (replace-html-characters "literally words &mdash; Graphing when your Facebook friends are awake")))))

(deftest html-characters-not-separated-by-spaces-are-correctly-replaced
  (testing "without spaces around html special character markup"
    (is (= "hurrdurr I'm a fancy blogger and have fancy “quotes”" (replace-html-characters "hurrdurr I'm a fancy blogger and have fancy &ldquo;quotes&rdquo;")))))
