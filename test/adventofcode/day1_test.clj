(ns adventofcode.day1-test
  (:require [clojure.test :refer :all]
            [adventofcode.day1 :refer :all]))

(deftest test-what-floor
 (testing "what floor?"
  (is (= 232 (what-floor?)))))
  
(deftest test-what-index
 (testing "what index"
  (is (= 1783 (what-index?)))))

