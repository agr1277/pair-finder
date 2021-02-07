(ns pair-finder.core
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.math.combinatorics :refer [combinations]]
            [clojure.data.csv :as csv])
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
       (map set)
       (frequencies)))

(defn format-output-row [[element-pair count]]
  (format "Seen: %d Times | Pair: %s, %s" count (first element-pair) (second element-pair)))

(defn -main
  [in-file-path out-file-path & args]
  (let [rows (->> in-file-path
                  load-rows-from-file
                  count-element-pairs
                  (filter #(<= 50 (val %)))
                  (sort-by val)
                  reverse)]
    (with-open [out (io/writer out-file-path)]
      (->> rows
           (map key)
           (csv/write-csv out)))
    (doseq [row rows]
      (println (format-output-row row)))
    (printf "%d pairs output to: [%s]%n" (count rows) out-file-path)))
