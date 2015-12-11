(ns adventofcode.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode.day10 :refer :all]))

(deftest test-processing-a-number
  (testing "processing a number"
    (is (= "111221" (process-number "1211")))))

