(ns adventofcode.day8-test
  (:require [clojure.test :refer :all]
            [adventofcode.day8 :refer :all]))

(deftest test-value-of-a
  (testing "final value of a"
    (is (= 1375 (total)))))

