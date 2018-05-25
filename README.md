[![Java 8](https://img.shields.io/badge/java-8-blue.svg)](http://java.oracle.com) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Build Status](https://travis-ci.org/Innovimax-SARL/mix-them.svg?branch=master)](https://travis-ci.org/Innovimax-SARL/mix-them)
[![codecov.io](https://codecov.io/github/Innovimax-SARL/mix-them/coverage.svg?branch=master)](https://codecov.io/github/Innovimax-SARL/mix-them?branch=master)
[![Code Climate](https://codeclimate.com/github/Innovimax-SARL/mix-them/badges/gpa.svg)](https://codeclimate.com/github/Innovimax-SARL/mix-them)
# mix-them
## Mix files togethers


  `mix-them [char|byte] <file1> <file2>[ <file3>... <fileN>]`
 
  will generate on standard out any file based on file1 to fileN    
  by default it assumes that all files are character based (not binary)
  
  `mix-them [char|byte] [-rule] <file1> <file2>[ <file3>... <fileN>]`
  
  will generate on standard out a file based on the rule
  
  `mix-them [char|byte] <index1> <index2>[ <index3>...] [-rule] <file1> <file2>[ <file3>... <fileN>]`
  
  will generate on standard out a file based on the rule and a selection of files designed by their index
  
  > Here are the list of rules
  - `\+`: will output selected files in order
  - `alt-line`: will output one line of each starting with first line of file1
  - `alt-char`: will output one char of each starting with first char of file1
  - `alt-byte`: will output one byte of each starting with first byte of file1
  - `random-alt-line [#seed]`: will output one line of each code randomly based on a seed for reproducability
  - `random-alt-char [#seed]`: will output one char of each code randomly based on a seed for reproducability
  - `random-alt-byte [#seed]`: will output one byte of each code randomly based on a seed for reproducability
  - `join [#cols]`: will output merging of lines that have common occurrence determined by a column index or first column by default
      - `join` will join on first column of each line
      - `join #2` will join on second column of each line
      - `join #2,` will join on second column of first line and first colum of other lines
      - `join #2,1` will join on second column of first line and first colum of other lines (same as previous)
      - `join #2,,3` will join on second column of first line, first colum of second line, third column of third line and first column of other lines
  - `zip-line [#sep]`: will output zip of char from file1 and file2 to fileN
  - `zip-char [#sep]`: will output zip of line from file1 and file2 to fileN 
  - `zip-cell [#sep]`: will output zip of cell from file1 and file2 to fileN   
  
## Mix files togethers from a zip/jar file

  `mix-them [char|byte] --zip zipfile`  
  `mix-them [char|byte] --jar jarfile`
  
  will generate on standard out any file based on entry1 to entryN of zip/jar file  
  by default it assumes zip/jar entries are character based (not binary)
  
  `mix-them [char|byte] [-rule] --zip zipfile`  
  `mix-them [char|byte] [-rule] --jar jarfile`
  
  will generate on standard out a file based on the rule
  
  `mix-them [char|byte] <index1> <index2>[ <index3>...] [-rule] --zip zipfile`  
  `mix-them [char|byte] <index1> <index2>[ <index3>...] [-rule] --jar jarfile`
  
  will generate on standard out a file based on the rule and a selection of entries designed by their index
