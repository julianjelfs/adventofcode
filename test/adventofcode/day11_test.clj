(ns adventofcode.day11-test
  (:require [clojure.test :refer :all]
            [adventofcode.day11 :refer :all]))

(deftest test-next-candidate
  (testing "getting the next candidate"
    (is (= "abcdefh" (next-candidate "abcdefg")))
    (is (= "abceaaa" (next-candidate "abcdzzz")))))

(deftest test-two-pairs
  (testing "contains two pairs"
    (is (= true (contains-two-pairs? "aaabbc")))
    (is (= false (contains-two-pairs? "asdfghjturidd")))
    (is (= false (contains-two-pairs? "aabnmaabnmaa")))
    (is (= false (contains-two-pairs? "qwertyuhjk")))
    (is (= true (contains-two-pairs? "123fdhhasasdppsdnn")))))

(deftest test-contains-straight
  (testing "contains straight"
    (is (= true (contains-straight? "acbdefg")))
    (is (= true (contains-straight? "jhdfhijbdfg")))
    (is (= false (contains-straight? "abxjtikshe")))
    (is (= false (contains-straight? "abdeghjk")))))

(deftest test-next-password
  (testing "get the next password"
    (is (= "cqjxxyzz" (next-password "cqjxjnds")))
    (is (= "cqkaabcc" (next-password "cqjxxyzz")))))
