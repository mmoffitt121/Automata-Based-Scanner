# Automata-Based Scanner
## Preface
This program was created for CS-3361 Concepts of Programming Languages at Texas Tech University.
## Problem
For this assignment, we were instructed to create an automata-based scanner that outputs a list of tokens if the program is scanned correctly, or [ERROR] if the program encounters an error. The program makes use of an input text file, and an input automata file.

## Usage
To use the scanner, open the CMD or Windows Powershell and navigate to the folder where the program is contained. To compile the program, run the following:

    javac scanner.java

Then, to run the program with an input file and input Automata, run:

    java scanner <input file.txt> <input automata.txt>

For the Example use case in the assignment paper, run:

    java scanner program.txt Automata.txt

## The Original Task
Next, we implemented the original task from the assignment as an automata file. To see this, run:

    java scanner program2.txt Automata2.txt

This will run a program based on the DFA on page 57 of the textbook. If you want to run any other programs using the automata speficied in the DFA, place your file in the directory of the program and run:

    java scanner <your program here> Automata2.txt