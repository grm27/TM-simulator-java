package com.grmit.tmsimulator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TapeTest {

    private static final int EXTENSION_FACTOR = 5;
    private Tape tape;

    @BeforeEach
    void setUp() {
        tape = Tape.of(new char[]{'a', 'b'}, EXTENSION_FACTOR);
    }

    @Test
    void readInBoundaries() {
        assertEquals('a', tape.read(0));
        assertEquals('b', tape.read(1));
    }

    @Test
    void readOutOfBoundaries() {
        assertEquals('_', tape.read(3));
        assertEquals('_', tape.read(-1));
    }

    @Test
    void writeInBoundaries() {
        tape.write(0, 'c');
        assertArrayEquals(new char[]{'c', 'b'}, tape.asCharArray());
    }

    @Test
    void writeOutOfBoundaries() {
        tape.write(2, 'c');
        tape.write(-1, 'c');
        assertArrayEquals(new char[]{'_', '_', '_', '_', 'c', 'a', 'b', 'c', '_', '_', '_', '_'}, tape.asCharArray());
        assertEquals('c', tape.read(2));
        assertEquals('c', tape.read(-1));
    }
}