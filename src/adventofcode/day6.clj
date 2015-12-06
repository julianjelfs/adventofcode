(ns adventofcode.day6
  (:require [clojure.string :as str]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(def on-re #"^turn on (\d+),(\d+) through (\d+),(\d+)$")
(def off-re #"^turn off (\d+),(\d+) through (\d+),(\d+)$")
(def toggle-re #"^toggle (\d+),(\d+) through (\d+),(\d+)$")

(defn map-match [k [_ fx fy fx1 fy1]]
  (for [x (range (parse-int fx) (inc (parse-int fx1)))
        y (range (parse-int fy) (inc (parse-int fy1)))]
    [[x y] k]))

(def grid 
  (let [row (vec (repeat 1000 :off))]
    (vec (repeat 1000 row))))

(defn apply-instructions [i g]
  (reduce (fn [g [k v]]
            (assoc-in g k v)) g i))


(defn parse-input []
  (let [lines (str/split-lines (slurp "src/adventofcode/day6"))]
    (loop [[line & the-rest] lines g grid]
      (if (empty? the-rest)
        (count  (filter (fn [state] (= state :on)) (flatten grid)))
        (let [on (re-find (re-matcher on-re line))
              off (re-find (re-matcher off-re line))
              toggle (re-find (re-matcher toggle-re line))
              instructions (cond
                             on (map-match :on on)
                             off (map-match :off off)
                             toggle (map-match :toggle toggle)
                             :else nil)]
          (recur the-rest (apply-instructions instructions g)))))))

