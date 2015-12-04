(ns adventofcode.day2
  (:require [clojure.string :as str]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(defn parse-dimensions [s]
 (let [dims (str/split s #"x")]
  (map parse-int dims)))
  
(defn surface-area [[l w h]]
 (let [a (* 2 l w)
       b (* 2 w h)
       c (* 2 h l)
       d (/ (min a b c) 2)]
  (+ a b c d)))
  
(defn parse-input []
  (let [lines (str/split-lines (slurp "src/adventofcode/day2"))]
    (map (comp surface-area parse-dimensions) lines)))
  
(defn total []
 (reduce + (parse-input))) 
 