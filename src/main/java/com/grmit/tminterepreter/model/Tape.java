package com.grmit.tminterepreter.model;

public class Tape {

    private char[] tape;

    public Tape(char[] tape) {
        this.tape = tape;
    }

    public char read(int index) {
        return tape[index];
    }

    public void write(int index) {

    }

}


