(ns adventofcode.day7
  (:require [clojure.string :as str]))

(def lines (str/split-lines (slurp "src/adventofcode/day7")))

(def ops {:AND bit-and
          :OR bit-or
          :LSHIFT bit-shift-left
          :RSHIFT bit-shift-right
          :NOT bit-not})

;;strategy is to create a map of operations then just incrementally fill them in
;; { key [operation & args] }
;; { :b [123]
;;   :c [bit-and :b 456]
;;   :d [bit-shift-left :c 3] }
;; when an op contains no keywords evaluate it:
;; (eval (seq (:b ops)))
;; to find value of we recursively find the value of all of it's arguments and then eval

(defn split-op [op]
  
  )

(defn parse-line [line]
  (let [[op target] (str/split line #" -> ")
        [func args] (split-op op)]
    [func args target]))

(defn value-of [wire]
  (let [parsed (map parse-line lines)])
  (loop [the-rest lines wv wire-values]
    (if ())
    )  
  )
