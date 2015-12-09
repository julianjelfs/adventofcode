(ns adventofcode.day8
  (:require [clojure.string :as str]))

(defn parse-int [s]
 (Integer. (re-find #"\d+" s)))  

(def lines (str/split-lines (slurp "src/adventofcode/day8")))

(defn num-characters [s]
  (count s))

(def replacements [[#"^\"" ""] [#"\"$" ""][#"\\x[0-9a-f]{2}" "x"] [#"\\\"" "*"] [#"\\\\" "@"] ])

(defn parse-line [s]
  (reduce (fn [result [re replacement]]
            (str/replace result re replacement)) s replacements))

(defn characters-in-memory [s]
  (count (parse-line s)))

(defn encode [l]
  (reduce + 2
          (map #(condp = %
                  \" 2
                  \\ 2
                  1)
               l)))

(defn total []
  (let [res  (reduce (fn [agg v]
            (assoc agg 
                   :recode (+ (encode v) (:recode agg))
                   :count (+ (num-characters v) (:count agg))
                   :mem (+ (characters-in-memory v) (:mem agg)))
            ) {:count 0 :mem 0 :recode 0} lines)]
    (- (:recode res) (:count res))))
