package com.grmit.tminterepreter.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TapeTest {

    private Tape tape;

    @BeforeEach
    void setUp() {
        tape = Tape.of(new char[]{'a', 'b'});
    }

    @Test
    void readInBoundary() {
        assertEquals('a', tape.read(0));
        assertEquals('b', tape.read(1));
    }

    @Test
    void readOutBoundary() {
        assertEquals('_', tape.read(3));
        assertEquals('_', tape.read(-1));
    }

    @Test
    void writeInBoundary() {
        tape.write(0, 'c');
        assertEquals('c', tape.read(0));
        assertEquals('b', tape.read(1));
    }

    @Test
    void writeOutBoundaryOnLeft() {
        tape.write(2, 'c');
        assertEquals('a', tape.read(0));
        assertEquals('b', tape.read(1));
        assertEquals('c', tape.read(2));
    }

    @Test
    void writeOutBoundaryOnRight() {
        tape.write(-1, 'c');
        assertEquals('c', tape.read(-1));
        assertEquals('a', tape.read(0));
        assertEquals('b', tape.read(1));
    }
}