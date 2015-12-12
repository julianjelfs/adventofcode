(ns adventofcode.day12-test
  (:require [clojure.test :refer :all]
            [adventofcode.day12 :refer :all]))

(deftest test-total
  (testing "get the total"
    (is (= 119433 (total)))))
