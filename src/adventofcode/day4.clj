(ns adventofcode.day4
  (:require [digest]))

(defn starts-with-00000? [h]
  (= "000000" (subs h 0 6)))

(defn lowest-number [seed] 
  (let [candidates (map #(str seed %) (range))]
    (first (filter (comp starts-with-00000? digest/md5) candidates))))
