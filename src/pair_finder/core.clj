(ns pair-finder.core
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.math.combinatorics :refer [combinations]])
  (:gen-class))

(defn parse-row [row]
  (s/split row #","))

(defn load-rows-from-file [filepath]
  (with-open [rdr (io/reader filepath)]
    (->> rdr
         line-seq
         (map parse-row)
         (into []))))

(defn count-element-pairs [rows]
  (->> rows
       (map #(combinations % 2))
       (apply concat)
       (map vec)
       (frequencies)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args])
