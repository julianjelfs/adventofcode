(ns adventofcode.day5-test
  (:require [clojure.test :refer :all]
            [adventofcode.day5 :refer :all]))

(deftest test-how-many-good
 (testing "how many goodies"
  (is (= 123 (number-of-goodies)))))
  

