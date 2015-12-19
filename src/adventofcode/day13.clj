(ns adventofcode.day13
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [clojure.math.combinatorics :as combo]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(def lines (str/split-lines (slurp "src/adventofcode/day13.txt")))

(def people 
  "get the distinct set of people"
  (conj (reduce (fn [people l]
            (conj people (keyword (first (str/split l #" ")))) 
            ) #{} lines) :Julian))

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

(defn happiness-level 
  "calculate happiness level for a permutation"
  [combo]
  (let [res (reduce (fn [agg p]
                      (let [prev (:prev agg)]
                        (if (or (= :Julian p) (= prev :Julian))
                          (assoc agg :prev p)
                          (let [t (:total agg)
                                r->l (get-in relations [p prev])
                                l->r (get-in relations [prev p])]
                            (assoc agg :prev p :total (+ t r->l l->r)))))
                      ) {:prev (last combo) :total 0} combo)]
    (:total res)))


(defn best-combination []
  (apply max (map happiness-level combos)))
