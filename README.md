# pair-finder
[![Build Status](https://travis-ci.com/agr1277/pair-finder.svg?branch=main)](https://travis-ci.com/agr1277/pair-finder)

This project aims to provide a utility to parse comma delimited files and find
common pairs of row elements.

For example, in these two lines:
```text
Radiohead,Pulp,Morrissey,Delays,Stereophonics,Blur,Suede,Sleeper,The La's,Super Furry Animals,Iggy Pop
Band of Horses,Smashing Pumpkins,The Velvet Underground,Radiohead,The Decemberists,Morrissey,Television
```
all combinations of musical artists are seen once, except for:
```text
[Radiohead, Morrissey]
```
which is seen twice.

## Algorithm

The implementation of this is relatively simple, and will count all 
occurrences of pairs. The core of the algorithm is: 
```clojure
(defn count-element-pairs [rows]
  (->> rows
       (map #(combinations % 2))
       (apply concat)
       (map set)
       (frequencies)))
```

For each row, compute all element combinations, collect all combinations, 
count the occurrences of all element combinations.

Given that an element combination may only occur in a given row **once**, 
the number of instances of that element is the same as the number of rows it 
appears in.

This implementation guarantees accurate accounting of combinations in the 
input rows, but must operate on every element in every row, and becomes 
memory expensive with very large input files.

#### Possible improvements

* For very large input files, a sampling of rows could be counted and then 
  extrapolated using statistical analysis.

## Usage

```shell
$ lein run [input_file_path] [output_file_path]
$ java -jar pair-finder-0.1.0-standalone.jar [input_file_path] [output_file_path]
```

## Examples

An example input file can be found in `resources`, this can be used with the 
`pair-finder`:
```shell
$ lein run resources/Artist_lists_small.txt out.csv
Seen: 73 Times | Pair: The Beatles, Radiohead
Seen: 70 Times | Pair: Radiohead, Muse
Seen: 69 Times | Pair: The Beatles, Muse
Seen: 66 Times | Pair: The Beatles, Nirvana
Seen: 64 Times | Pair: Muse, The Killers
Seen: 62 Times | Pair: Coldplay, Muse
Seen: 62 Times | Pair: The Beatles, Pink Floyd
Seen: 59 Times | Pair: Arctic Monkeys, Muse
Seen: 58 Times | Pair: Muse, Radiohead
Seen: 57 Times | Pair: Radiohead, Coldplay
Seen: 57 Times | Pair: The Beatles, Coldplay
Seen: 56 Times | Pair: The Beatles, Arctic Monkeys
Seen: 56 Times | Pair: Lady Gaga, Rihanna
Seen: 55 Times | Pair: The Beatles, The Killers
Seen: 54 Times | Pair: Muse, Arctic Monkeys
Seen: 52 Times | Pair: The Beatles, The Rolling Stones
Seen: 51 Times | Pair: Muse, Coldplay
Seen: 50 Times | Pair: The Beatles, Led Zeppelin
Seen: 50 Times | Pair: Radiohead, Arctic Monkeys
19 pairs output to: [out.csv]
```
