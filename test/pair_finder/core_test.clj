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

(deftest test-count-pairs
  (testing "counts the occurrences of elements seen together in a vector of element vectors"
    (let [test-rows [["A" "B" "C"]
                     ["A" "B" "D"]
                     ["B" "D" "C"]]]
      (is (= {#{"A" "B"} 2
              #{"A" "C"} 1
              #{"B" "C"} 2
              #{"A" "D"} 1
              #{"B" "D"} 2
              #{"D" "C"} 1} (count-element-pairs test-rows))))))

(deftest test-format-output-row
  (testing "properly formats an output row"
    (is (= "Seen: 5 Times | Pair: A, B" (format-output-row [["A" "B"] 5])))))