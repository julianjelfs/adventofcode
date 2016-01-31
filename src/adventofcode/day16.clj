(ns adventofcode.day16
    (:require [clojure.string :as str]
      [clojure.set :as set]))

(def evidence {
   :children 3
   :cats 7
   :samoyeds 2
   :pomeranians 3
   :akitas 0
   :vizslas 0
   :goldfish 5
   :trees 3
   :cars 2
   :perfumes 1
 })

(def match-fns {
    :children =
    :cats >
    :samoyeds =
    :pomeranians <
    :akitas =
    :vizslas =
    :goldfish <
    :trees >
    :cars =
    :perfumes =
})

(def lines (str/split-lines (slurp "src/adventofcode/day16.txt")))

(defn line->map [l]
      (let [[sue attributes] (drop 1 (re-find #"Sue (\d+): (.+)" l))
            split-attr (str/split attributes #", ")]
           (reduce (fn [agg attr]
                       (let [[k v] (str/split attr #": ")]
                            (assoc agg (keyword k) (Integer. v)))) {:sue sue} split-attr)))

(defn sue-matches? [sue]
      (reduce (fn [match [k v]]
                  (and match ((k match-fns) v (k evidence)))) true (drop 1 sue)))

(def candidates
  (let [sues (map line->map lines)]
       (filter sue-matches? sues)))


