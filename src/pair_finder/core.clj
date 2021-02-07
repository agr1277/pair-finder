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

(defn format-output-row [element-pair count]
  (format "Seen: %d Times | Pair: %s, %s" count (first element-pair) (second element-pair)))

(defn -main
  [filename & args]
  (let [output-rows (->> filename
                         load-rows-from-file
                         count-element-pairs
                         (filter #(<= 50 (val %)))
                         (sort-by val)
                         (reverse)
                         (map #(format-output-row (key %) (val %))))]
    (doseq [row output-rows]
      (println row))))
