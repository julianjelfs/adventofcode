(ns adventofcode.day13-test
  (:require [clojure.test :refer :all]
            [adventofcode.day13 :refer :all]))

(deftest test-best-combination
  (testing "best combination"
    (is (= 898 (best-combination)))))

