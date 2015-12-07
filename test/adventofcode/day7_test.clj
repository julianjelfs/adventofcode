(ns adventofcode.day7-test
  (:require [clojure.test :refer :all]
            [adventofcode.day7 :refer :all]))

(deftest test-value-of-a
  (testing "final value of a"
    (is (= 2797 (value-of :a)))))

