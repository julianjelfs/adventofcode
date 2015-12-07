(ns adventofcode.day6-test
  (:require [clojure.test :refer :all]
            [adventofcode.day6 :refer :all]))

(deftest test-overall-light-level
  (testing "overall light level"
    (is (= 14110788 (parse-input)))))

