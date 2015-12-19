(ns adventofcode.day13
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(def lines (str/split-lines (slurp "src/adventofcode/day13.txt")))

(def people 
  "get the distinct set of people"
  (reduce (fn [people l]
            (conj people (first (str/split l #" "))) 
            ) #{} lines))

(def combos 
  "calculate all of the possible combinations"
  (combo/permutations people))

(def ops {"lose" - "gain" +})

(def relations 
  "build a map of relations between all of the people"
  (reduce (fn [d l]
            (let [[a _ c n _ _ _ _ _ _ b] (map #(str/replace % #"\." "") (str/split l #" "))
                  ka (keyword a)
                  kb (keyword b)
                  op (get ops c)
                  person (ka d)]
              (assoc d ka (assoc person kb (op (parse-int n)))))) {} lines))

(defn happines-level 
  "calculate happiness level for a permutation"
  [combo]
  )


(defn best-combination []
  (apply min (map happiness-level combos)))
