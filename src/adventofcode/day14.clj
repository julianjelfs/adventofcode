(ns adventofcode.day14
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def lines (str/split-lines (slurp "src/adventofcode/day14.txt")))

(defn parse-line [l]
  (map read-string (re-seq #"\d+" l)))

(def reindeer (map parse-line lines))

(defn distance [[sp st rst] seconds]
  (let [interval (+ st rst)
        last-interval (rem seconds interval)]
    (+ (* st sp (int (/ seconds interval)))
       (if (> st last-interval)
         (* sp last-interval)
         (* sp st)))))

(defn furthest []
  (reduce max (map #(distance % 2503) reindeer)))

(defn winners [reindeer seconds]
  (let [res (apply merge-with concat
                   (map
                    #(hash-map (distance % seconds) [%])
                    reindeer))]
    (get res (apply max (keys res)))))

(defn winning-points []
  (reduce max
          (vals
            (frequencies
              (mapcat (partial winners reindeer)
                      (range 1 (inc 2503)))))))
