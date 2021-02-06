(ns pair-finder.core-test
  (:require [clojure.test :refer :all]
            [pair-finder.core :refer :all]))

(deftest test-parse-row
  (testing "parse line returns a vector containing elements"
    (is (= ["A" "B" "C" "1" "2" "3"] (parse-row "A,B,C,1,2,3")))))

(deftest test-get-rows-from-file
  (testing "loads rows from a test file succesfully"
    (is (= [["A" "B" "C" "D" "E"]
            ["1" "2" "3" "4"]
            ["Hello" "World"]] (load-rows-from-file "test/resources/test_rows.txt")))))