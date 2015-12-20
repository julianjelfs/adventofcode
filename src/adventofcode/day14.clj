(ns adventofcode.day14
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(def lines (str/split-lines (slurp "src/adventofcode/day14.txt")))

(def reindeers 
  "build a map of reindeer stats"
  (reduce (fn [agg l]
            (let [[r _ _ s _ _ st _ _ _ _ _ _ rt _] (str/split l #" ")]
              (assoc agg (keyword r) {:speed (parse-int s) 
                                      :stamina (parse-int st) 
                                      :rest (parse-int rt)}))) {} lines))

;; { :rudolph {:speed 22 :stamina 8 :rest 165}}

(defn distance [seconds reindeer]
  (let [r (reindeer reindeers)
        stamina (:stamina r)
        resting (:rest r)
        speed (:speed r)
        ticks (flatten (repeatedly #(vec [stamina resting])))]
    (loop [elapsed 0
           moving true
           countdown (first ticks)
           next-ticks (rest ticks)
           travelled 0]
      (let [counting (> countdown 1)]
        (if (= elapsed seconds)
          travelled
          (recur 
            (inc elapsed)
            (if counting moving (not moving))
            (if counting (dec countdown) (first next-ticks))
            (if counting next-ticks (rest next-ticks))
            (if moving (+ speed travelled) travelled)))))))

(defn max-distance [n]
  (apply max (map (partial distance n) (keys reindeers))))
