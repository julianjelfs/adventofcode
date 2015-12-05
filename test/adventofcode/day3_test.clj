(ns adventofcode.day3-test
  (:require [clojure.test :refer :all]
            [adventofcode.day3 :refer :all]))

(deftest parse-input-test
 (testing "parsing the input"
  (is (= 8193 (count (parse-input))))))
  
(deftest test-houses-visited
 (testing "how many houses visited"
  (is (= 8193 (houses-visited)))))
  

