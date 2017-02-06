[![Build Status](https://travis-ci.org/Innovimax-SARL/mix-them.svg?branch=master)](https://travis-ci.org/Innovimax-SARL/mix-them)

# mix-them
Mix files togethers


    mix-them file1 file2 
  will generate on standard out any file based on file1 and file2.
  by default it assumes file1 and file2 are character based (not binary)
  
    mix-them -[rule] file1 file2
  will generate on standard out a file based on the rule
  
  Here are the list of rules
  - 1 : will output file1
  - 2 : will output file2
  - alt-line : will output one line of each starting with first line of file1
  - alt-byte : will output one byte of each starting with first byte of file1
  - random-alt-line [seed] : will output one line of each code randomly based on a seed for reproducability
  - join : will output merging of lines that have common occurrence
