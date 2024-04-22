package com.grmit.tmsimulator.model;

import java.util.Arrays;

public class Tape {
    private static final int EXTENSION_FACTOR = 5;
    private int offset = 0;
    private char[] tape;

    public static Tape of(char[] initArray){
        return new Tape(initArray);
    }

    private Tape(char[] tape) {
        this.tape = tape;
    }

    public char read(int index) {
        if (index + offset < 0 || index >= tape.length)
            return '_';
        return tape[index + offset];
    }

    public void write(int index, char character) {

        if (index >= tape.length) {
            tape = Arrays.copyOf(tape, index + EXTENSION_FACTOR);
            Arrays.fill(tape, index + 1, tape.length - 1, '_');
        }

        if (index + offset < 0) {
            tape = Arrays.copyOf(tape, tape.length + EXTENSION_FACTOR);
            shiftArray(tape);
            Arrays.fill(tape, 0, EXTENSION_FACTOR - 1, '_');
            offset += EXTENSION_FACTOR;
        }

        tape[index + offset] = character;
    }

    private void shiftArray(char[] array) {
        assert EXTENSION_FACTOR < array.length;
        for (int i = 1; i <= array.length - EXTENSION_FACTOR; i++) {
            array[array.length - i] = array[array.length - i - EXTENSION_FACTOR];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tape tape1 = (Tape) o;
        return Arrays.equals(tape, tape1.tape);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tape);
    }
}


