(ns adventofcode.day3-test
  (:require [clojure.test :refer :all]
            [adventofcode.day3 :refer :all]))

(deftest test-houses-visited
 (testing "how many houses visited"
  (is (= 2081 (houses-visited)))))
  

