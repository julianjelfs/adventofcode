(ns adventofcode.day5-test
  (:require [clojure.test :refer :all]
            [adventofcode.day5 :refer :all]))

(deftest test-at-least-three-vowels
  (testing "at least three vowels"
    (is (= true (at-least-three-vowels? "akjhihdfodfkjee")))
    (is (= false (at-least-three-vowels? "sdfsdfdf")))))

(deftest test-no-illegal-strings
  (testing "no illegal strings"
    (is (= true (no-illegal-strings? "akjhihdfodfkjee")))
    (is (= false (no-illegal-strings? "abdkjhdfgxydfg")))))

(deftest test-at-least-one-dupe
  (testing "at least one dupe"
    (is (= true (at-least-one-dupe? "kdfghaiikdbsnn")))
    (is (= false (at-least-one-dupe? "asdfghjbnmc")))))

(deftest test-repeat-with-separator
  (testing "repeat with separator"
    (is (= true (repeat-with-separator? "qjhvhtzxzqqjkmpb")))
    (is (= true (repeat-with-separator? "sdfaba")))
    (is (= false (repeat-with-separator? "asdfghjkl")))))

(deftest test-duplicate-pairs
  (testing "duplicate pairs"
    (is (= true (duplicate-pairs? "qjhvhtzxzqqjkmpb")))
    (is (= true (duplicate-pairs? "xxkjdfgkjxx")))
    (is (= false (duplicate-pairs? "aaaxxx")))
    (is (= false (duplicate-pairs? "ksdjhfbsjwus")))))

(deftest test-how-many-good
 (testing "how many goodies"
  (is (= 69 (number-of-goodies)))))

(deftest test-good?
  (testing "good?"
    (is (= true (good? "qjhvhtzxzqqjkmpb")))
    (is (= false (good? "uurcxstgmygtbstg")))
    (is (= false (good? "ieodomkazucvgmuy")))
    (is (= true (good? "xxyxx")))))
  

