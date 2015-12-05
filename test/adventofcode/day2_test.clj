(ns adventofcode.day2-test
  (:require [clojure.test :refer :all]
            [adventofcode.day2 :refer :all]))

(deftest test-total
 (testing "Total"
  (is (= 1586300 (total)))))
  
(deftest test-total-ribbon
 (testing "Total Ribbon"
  (is (= 3737498 (total-ribbon)))))
   
    
     
