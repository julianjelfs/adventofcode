(ns adventofcode.day12
  (:require [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.walk :as w]))

(def doc (json/read-str (slurp "src/adventofcode/day12.txt")))


(defn total []
  (let [t (atom 0)]
    (w/postwalk #(if (number? %) (swap! t + %) %) doc)
    @t))

