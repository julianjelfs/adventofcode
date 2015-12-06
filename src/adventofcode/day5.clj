(ns adventofcode.day5
  (:require [clojure.string :as str]))

(defn vowel? [c]
  (or (= \a c) (= \e c) (= \i c) (= \o c) (= \u c)))

(defn number-of-vowels [s]
  (count (filter vowel? s)))

(defn at-least-three-vowels? [s]
  (>= (number-of-vowels s) 3))

(defn at-least-one-dupe? [s]
  (loop [s s]
    (if (empty? s)
      false
      (let [fst (first s)
            snd (second s)
            tail (rest s)]
        (if (= fst snd)
          true
          (recur tail))))))

(defn no-illegal-strings? [s]
  (not (or (re-find #"ab" s)
           (re-find #"cd" s)
           (re-find #"pq" s)
           (re-find #"xy" s))))

(defn repeat-with-separator? [s]
  (loop [s s]
    (if (empty? s)
      false
      (let [[fst snd thr] s
            tail (rest s)]
        (if (= fst thr)
          true
          (recur tail))))))

(defn duplicate-pairs? [s]
  (loop [s s]
    (if (empty? s)
      false
      (let [[fst snd & xtail] s
            tail (rest s)
            remainder (apply str xtail)]
        (if (>= (.indexOf remainder (str fst snd)) 0)
          true
          (recur tail))))))

;(defn good? [s]
;  (and 
;    (at-least-three-vowels? s)
;    (at-least-one-dupe? s)
;    (no-illegal-strings? s)))

(defn good? [s]
  (and 
    (repeat-with-separator? s)
    (duplicate-pairs? s)))

(defn number-of-goodies []
  (let [lines (str/split-lines (slurp "src/adventofcode/day5"))]
    (count (filter good? lines))))

