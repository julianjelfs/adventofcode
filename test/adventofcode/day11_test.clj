(ns adventofcode.day11-test
  (:require [clojure.test :refer :all]
            [adventofcode.day11 :refer :all]))

(deftest test-next-candidate
  (testing "getting the next candidate"
    (is (= "abcdefh" (next-candidate "abcdefg")))
    (is (= "abdeaaa" (next-candidate "abcdzzz")))))

