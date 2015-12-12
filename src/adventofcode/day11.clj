(ns adventofcode.day11
  (:require [clojure.string :as str]))

(defn next-candidate [current]
  (let [rev (reverse current)]
    (loop [[h & t] rev
           result ""]
      (let [c (int h)]
        (if (not (= h \z))
          (apply str (reverse (str result (char (inc c)) (apply str t))))
          (recur t (str result \a)))))))

(defn no-banned-chars? [candidate]
  (not (re-find #"[i,l,o]" candidate)))

(defn contains-straight? [candidate]
  true)

(defn longer-than-one? [col]
  (> (count col) 1))

;;this might not be good enough because "aa" and "aaa" will not be considered duplicates
(defn contains-two-pairs? [candidate]
  (let [p (partition-by identity candidate)
        de-duped (into #{} (filter longer-than-one? p))]
  (> (count de-duped) 1)))

(defn candidate-valid? [candidate]
  (and 
    (contains-straight? candidate)
    (contains-two-pairs? candidate) 
    (no-banned-chars? candidate)))

(defn next-password [current]
  (loop [n (next-candidate current)]
    (if (candidate-valid? n)
      n
      (recur (next-candidate n)))))

; (defn vowel? [c]
;   (or (= \a c) (= \e c) (= \i c) (= \o c) (= \u c)))

; (defn number-of-vowels [s]
;   (count (filter vowel? s)))

; (defn at-least-three-vowels? [s]
;   (>= (number-of-vowels s) 3))

; (defn at-least-one-dupe? [s]
;   (loop [s s]
;     (if (empty? s)
;       false
;       (let [fst (first s)
;             snd (second s)
;             tail (rest s)]
;         (if (= fst snd)
;           true
;           (recur tail))))))

; (defn no-illegal-strings? [s]
;   (not (or (re-find #"ab" s)
;            (re-find #"cd" s)
;            (re-find #"pq" s)
;            (re-find #"xy" s))))

; (defn repeat-with-separator? [s]
;   (loop [s s]
;     (if (empty? s)
;       false
;       (let [[fst snd thr] s
;             tail (rest s)]
;         (if (= fst thr)
;           true
;           (recur tail))))))

; (defn duplicate-pairs? [s]
;   (loop [s s]
;     (if (empty? s)
;       false
;       (let [[fst snd & xtail] s
;             tail (rest s)
;             remainder (apply str xtail)]
;         (if (>= (.indexOf remainder (str fst snd)) 0)
;           true
;           (recur tail))))))

;(defn good? [s]
;  (and 
;    (at-least-three-vowels? s)
;    (at-least-one-dupe? s)
;    (no-illegal-strings? s)))

; (defn good? [s]
;   (and 
;     (repeat-with-separator? s)
;     (duplicate-pairs? s)))

; (defn number-of-goodies []
;   (let [lines (str/split-lines (slurp "src/adventofcode/day5"))]
;     (count (filter good? lines))))

