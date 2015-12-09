(ns adventofcode.day9
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(defn parse-line [places line]
  (let [[from _ to] (str/split line #" ")]
    (conj (conj places from) to)))

(def lines (str/split-lines (slurp "src/adventofcode/day9")))
(def routes (combo/permutations (reduce parse-line #{} lines)))

(def distances (reduce (fn [d l]
                        (let [[f _ t _ dist] (str/split l #" ")
                              kf (keyword f)
                              kt (keyword t)
                              from (if (kf d) (kf d) {})]
                          (assoc d kf (assoc from kt (parse-int dist))))) {} lines))

(defn distance-between [p n]
  (let [d (get-in distances [(keyword p) (keyword n)])
        d1 (get-in distances [(keyword n) (keyword p)])]
    (if d d d1)))

(defn route-length [route]
  (:distance (reduce (fn [agg place]
            (if (:previous agg)
              (assoc agg :previous place :distance (+ (:distance agg) (distance-between (:previous agg) place)))
              (assoc agg :previous place))) {:distance 0} route)))

(defn shortest []
  (apply max (map route-length routes)))
