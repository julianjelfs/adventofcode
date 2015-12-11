(ns adventofcode.day10)

(defn process-number [s]
  (let [p (partition-by identity s)]
    (reduce (fn [s c] (str s (count c) (first c))) "" p)))

(defn loop-40 [seed]
  (loop [i 0 inp seed]
    (if (< i 50)
      (recur (inc i) (process-number inp))
      (count inp))))

