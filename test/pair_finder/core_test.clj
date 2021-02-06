(ns pair-finder.core-test
  (:require [clojure.test :refer :all]
            [pair-finder.core :refer :all]))

(deftest test-parse-row
  (testing "parse line returns a vector containing elements"
    (is (= ["A" "B" "C" "1" "2" "3"] (parse-row "A,B,C,1,2,3")))))
