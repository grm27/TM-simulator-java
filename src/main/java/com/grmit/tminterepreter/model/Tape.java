package com.grmit.tminterepreter.model;

public class Tape {

    private char[] tape;

    public Tape(char[] tape) {
        this.tape = tape;
    }

    public char read(int index) {
        if (index < 0 || index >= tape.length)
            return '_';
        return tape[index];
    }

    public void write(int index, char character) {
        tape[index] = character;
    }

}


