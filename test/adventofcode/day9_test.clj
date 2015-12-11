(ns adventofcode.day9-test
  (:require [clojure.test :refer :all]
            [adventofcode.day9 :refer :all]))

(deftest test-shortest
  (testing "shortest value"
    (is (= 898 (shortest)))))

