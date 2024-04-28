package com.grmit.tmsimulator.model;

import java.util.Arrays;

public class Tape implements Cloneable {
    private final int extensionFactor;
    private int offset = 0;
    private char[] tape;

    public static Tape of(char[] initArray) {
        return new Tape(initArray, 5);
    }

    public static Tape of(char[] initArray, int extensionFactor) {
        return new Tape(initArray, extensionFactor);
    }

    private Tape(char[] tape, int extensionFactor) {
        this.tape = tape;
        this.extensionFactor = extensionFactor;
    }

    public char read(int index) {
        if (index + offset < 0 || index + offset >= tape.length)
            return '_';
        return tape[index + offset];
    }

    public void write(int index, char character) {

        if (index + offset >= tape.length) {
            tape = Arrays.copyOf(tape, index + offset + extensionFactor);
            Arrays.fill(tape, index + offset, tape.length, '_');
        }

        if (index + offset < 0) {
            tape = Arrays.copyOf(tape, tape.length + extensionFactor);
            shiftArray(tape);
            Arrays.fill(tape, 0, extensionFactor - 1, '_');
            offset += extensionFactor;
        }

        tape[index + offset] = character;
    }

    public char[] asCharArray() {
        return tape;
    }

    private void shiftArray(char[] array) {
        assert extensionFactor < array.length;
        for (int i = 1; i <= array.length - extensionFactor; i++) {
            array[array.length - i] = array[array.length - i - extensionFactor];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tape tape1 = (Tape) o;
        return tape == tape1.tape;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tape);
    }

    @Override
    public Tape clone() {
        try {
            Tape clone = (Tape) super.clone();
            clone.tape = this.tape.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Tape{" +
            "tape=" + Arrays.toString(tape) +
            '}';
    }
}


