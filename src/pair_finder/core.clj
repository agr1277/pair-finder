(ns pair-finder.core
  (:require [clojure.string :as s])
  (:gen-class))

(defn parse-row [row]
  (s/split row #","))

(defn -main
  "I don't do a whole lot ... yet."
  [& args])
