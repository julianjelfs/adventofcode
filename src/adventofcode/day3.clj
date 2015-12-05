(ns adventofcode.day3
  (:require [clojure.string :as str]))

(defn parse-input []
  (let [directions (slurp "src/adventofcode/day3")]
    directions))

(defn north [[x y]]
  [x (inc y)])

(defn south [[x y]]
  [x (dec y)])

(defn east [[x y]]
  [(inc x) y])

(defn west [[x y]]
  [(dec x) y])

(def moves {\^ north \v south \> east \< west})

(defn process-instructions [pos instructions visited]
  (if (empty? instructions)
    visited
    (let [[h & t] instructions
          next-pos ((get moves h) pos)]
      (process-instructions next-pos t (conj visited next-pos)))))

(defn houses-visited []
  (let [visited (process-instructions [0 0] (parse-input) #{[0 0]})]
    (count visited)))
  
