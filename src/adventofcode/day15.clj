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

(defn aspect-val [aspect spoons]
  (max 0 (* aspect spoons)))

(defn ingredient-score [ingredient spoons]
  (let [[cap dur flav txt _] (ingredient ingredients)]
    ;this looks like a lot like a reduce
    (* (aspect-val cap spoons)
       (aspect-val dur spoons)
       (aspect-val flav spoons)
       (aspect-val txt spoons))))

(defn recipe-score [[sp pb fr sg]]
  ;this also looks a lot like a reduce
  (+ (ingredient-score :Sprinkles sp)
     (ingredient-score :PeanutButter pb)
     (ingredient-score :Frosting fr)
     (ingredient-score :Sugar sg)))

(defn best-recipe []
  (apply max (map recipe-score (combos 100))))
