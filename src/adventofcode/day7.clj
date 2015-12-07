(ns adventofcode.day7
  (:require [clojure.string :as str]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(defn isnum? [s]
  (re-find #"^\d+$", s))

(def lines (str/split-lines (slurp "src/adventofcode/day7")))

(def ops {:AND bit-and
          :OR bit-or
          :LSHIFT bit-shift-left
          :RSHIFT bit-shift-right
          :NOT bit-not})

(defn keywordise [parts]
  (map #(if (isnum? %) (parse-int %) (keyword %)) parts))

(defn split-op [op]
  (let [parts (keywordise (str/split op #" "))
        [f s t] parts]
    (condp = (count parts)
      1 [f]
      2 [(f ops) s]
      3 [(s ops) f t]
      [])))

(defn parse-line [agg line]
  (let [[op target] (str/split line #" -> ")]
    (assoc agg (keyword target) (split-op op))))

(def expressions (reduce parse-line {} lines))

(defn eval-expr [expr]
  (condp = (count expr)
    1 (first expr)
    (eval (seq expr))))

(def resolved (atom {}))

(defn value-of [wire]
  (if (wire @resolved)
    (wire @resolved)
    (let [expr (wire expressions)
          incomplete (some keyword? expr)]
      (if incomplete
        (let [filled (map (fn [e]
                            (if (keyword? e)
                              (value-of e)
                              e)) expr)]
          (swap! resolved assoc wire (eval-expr filled)))
        (swap! resolved assoc wire  (eval-expr expr))))))
