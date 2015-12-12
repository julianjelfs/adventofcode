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
  (let [res (reduce (fn [agg c]
                      (let [i (int c)
                            p (:previous agg)
                            s (:streak agg)]
                        (if (> s 2)
                          agg
                          (if (= i (inc p))
                              (assoc agg :streak (inc s) :previous i)
                              (assoc agg :streak 1 :previous i))))) {:previous 0 :streak 1} candidate)]
  (> (:streak res) 2)))

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

