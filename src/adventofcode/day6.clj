(ns adventofcode.day6
  (:require [clojure.string :as str]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(def on-re #"^turn on (\d+),(\d+) through (\d+),(\d+)$")
(def off-re #"^turn off (\d+),(\d+) through (\d+),(\d+)$")
(def toggle-re #"^toggle (\d+),(\d+) through (\d+),(\d+)$")
(def opposite {:on :off :off :on})

(defn map-match [k [_ fx fy fx1 fy1]]
  (for [x (range (parse-int fx) (inc (parse-int fx1)))
        y (range (parse-int fy) (inc (parse-int fy1)))]
    [[x y] k]))

(def grid 
  (let [row (vec (repeat 1000 0))]
    (vec (repeat 1000 row))))

(defn apply-instructions [i g]
  (reduce (fn [g [k v]]
            (let [curr (get-in g k)]
              (cond 
                (= v :toggle) (assoc-in g k (+ 2 curr))
                (= v :on) (assoc-in g k (inc curr))
                (= v :off) (if (> curr 0) (assoc-in g k (dec curr)) g) 
                :else g))) g i))

(def lines (str/split-lines (slurp "src/adventofcode/day6")))

(defn parse-input []
  (loop [the-rest lines g grid]
    (if (empty? the-rest)
      (reduce + (flatten g))
      (let [line (first the-rest)
            on (re-find (re-matcher on-re line))
            off (re-find (re-matcher off-re line))
            toggle (re-find (re-matcher toggle-re line))
            instructions (cond
                           on (map-match :on on)
                           off (map-match :off off)
                           toggle (map-match :toggle toggle)
                           :else nil)]
        (recur (rest the-rest) (apply-instructions instructions g))))))

