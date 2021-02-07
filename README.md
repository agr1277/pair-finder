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


## Usage

    $ lein run [input_file_path]
    $ java -jar pair-finder-0.1.0-standalone.jar [input_file_path]

## Examples

An example input file can be found in `resources`, this can be used with the 
`pair-finder`:

    $ lein run resources/Artist_lists_small.txt 