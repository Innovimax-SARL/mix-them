[![Java 8](https://img.shields.io/badge/java-8-blue.svg)](http://java.oracle.com) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Build Status](https://travis-ci.org/Innovimax-SARL/mix-them.svg?branch=master)](https://travis-ci.org/Innovimax-SARL/mix-them)
[![codecov.io](https://codecov.io/github/Innovimax-SARL/mix-them/coverage.svg?branch=master)](https://codecov.io/github/Innovimax-SARL/mix-them?branch=master)
[![Code Climate](https://codeclimate.com/github/Innovimax-SARL/mix-them/badges/gpa.svg)](https://codeclimate.com/github/Innovimax-SARL/mix-them)
# mix-them
Mix files togethers


    mix-them file1 file2 
  will generate on standard out any file based on file1 and file2.
  by default it assumes file1 and file2 are character based (not binary)
  
    mix-them [-rule] file1 file2
  will generate on standard out a file based on the rule
  
  Here are the list of rules
  - 1: will output file1
  - 2: will output file2
  - +: will output file1+file2
  - alt-line: will output one line of each starting with first line of file1
  - alt-char: will output one char of each starting with first char of file1
  - random-alt-line [#seed]: will output one line of each code randomly based on a seed for reproducability
  - join [#col1] [#col2]: will output merging of lines that have common occurrence
  - zip-line [#sep]: will output zip of char from file1 and file2 
  - zip-char [#sep]: will output zip of line from file1 and file2 
  - zip-cell [#sep]: will output zip of cell from file1 and file2 

Mix files togethers from a zip/jar file

    mix-them --zip zipfile 
    mix-them --jar zipfile 
  will generate on standard out any file based on first entrie and second entrie of zip/jar file.
  by default it assumes zip/jar entries are character based (not binary)
  
    mix-them [-rule] --zip zipfile
    mix-them [-rule] --jar jarfile
  will generate on standard out a file based on the rule
