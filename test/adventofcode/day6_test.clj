(ns adventofcode.day6-test
  (:require [clojure.test :refer :all]
            [adventofcode.day6 :refer :all]))

(deftest test-at-least-three-vowels
  (testing "at least three vowels"
    (is (= true (at-least-three-vowels? "akjhihdfodfkjee")))
    (is (= false (at-least-three-vowels? "sdfsdfdf")))))

