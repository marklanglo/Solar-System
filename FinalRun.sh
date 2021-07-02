#!/bin/bash

#Program name: Final Program
#Author: Mark Wiedeman
#Email: markwiedeman5@fullerton.edu
#File name:  FinalRun.sh
#Preconditions:
#   1.  All source files are in one directory
#   2.  This file, FinalRun.sh, is in that same directory
#   3.  The shell is currently active in that same directory
#How to execute: Enter "sh FinalRun.sh" without the quotes.

echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile FinalGraphics.java
javac FinalGraphics.java

echo Compile FinalUI.java
javac FinalUI.java

echo Compile FinalMain.java
javac FinalMain.java

echo Execute the Final program
java FinalMain

echo Program successfully terminated. Thank you for running my Final program.
