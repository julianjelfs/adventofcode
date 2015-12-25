(ns adventofcode.day15
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def lines (str/split-lines (slurp "src/adventofcode/day15.txt")))

(def ingredients 
  (reduce (fn [agg l]
           (let [[i propStr] (str/split l #":")
                 props (map read-string (re-seq #"-?\d+" propStr))]
             (assoc agg (keyword i) props)) 
            ) {} lines))

(defn combos [total]
  (for [a (range (inc total))
        b (range (inc (- total a)))
        c (range (inc (- total a b)))
        d (range (inc (- total a b c)))]
    [a b c d]))

(defn aspect-val [spoons aspect]
  (max 0 (* aspect spoons)))

(defn ingredient-score [spoons ingredient]
    (map (partial aspect-val spoons) (ingredient ingredients)))

(defn recipe-score [[sp pb fr sg]]
  (->> (map (partial ingredient-score sp) [:Sprinkles :PeanutButter :Frosting :Sugar])
       (reduce #(map + %1 %2))
       (reduce *)))

(defn best-recipe []
  (apply max (map recipe-score (combos 100))))
