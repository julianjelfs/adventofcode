(ns adventofcode.day17
    (:require [clojure.string :as str]
      [clojure.math.combinatorics :as combo]))

(def lines (map #(Integer. %) (str/split-lines (slurp "src/adventofcode/day17.txt"))))

(defn sum-is-150? [c]
      (= 150 (reduce + c)))

;;this is no good because it excludes duplicates
(defn num-combos []
      (let [combos (combo/subsets lines)
            matching (filter sum-is-150? combos)]
           (count matching)))