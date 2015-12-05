(ns adventofcode.day1
  (:require [clojure.string :as str]))

(defn up-or-down [state p]
  (let [updated (assoc state :index (inc (:index state)))]
    (if (= \( p)
      (assoc updated :up (inc (:up updated)))
      (assoc updated :down (inc (:down updated))))))
  
(defn parse-input []
    (loop [state {:up 0 :down 0 :index 0 :stop false :basement 0} parens (slurp "src/adventofcode/day1")]
      (if (empty? parens)
        state
        (let [[h & t] parens
              new-state (up-or-down state h)
              up (:up new-state)
              down (:down new-state)]
          (if (and (< (- up down) 0) (not (:stop state)))
            (recur (assoc new-state :basement (:index state) :stop true) t)
            (recur new-state t))))))

(defn what-floor? []
  (let [res (parse-input)]
    (- (:up res) (:down res))))

(defn what-index? []
  (let [res (parse-input)]
    (inc (:basement res))))
 
