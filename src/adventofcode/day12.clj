(ns adventofcode.day12
  (:require [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.walk :as w]))

(def doc (json/read-str (slurp "src/adventofcode/day12.txt")))

(defn no-red [doc]
  (w/prewalk (fn [n]
               (if (map? n)
                 (if (some #(= "red" %) (vals n)) 
                   {}
                   n)
                 n)) doc))

(defn total []
  (let [t (atom 0)]
    (w/prewalk #(if (number? %) (swap! t + %) %) (no-red doc))
    @t))

