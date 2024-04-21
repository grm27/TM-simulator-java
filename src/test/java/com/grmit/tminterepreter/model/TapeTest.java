package com.grmit.tminterepreter.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TapeTest {

    private Tape tape;

    @BeforeEach
    void setUp() {
        tape = new Tape(new char[]{'a', 'b'});
    }

    @Test
    void readInBoundary() {
        assertEquals('a', tape.read(0));
    }

    @Test
    void readOutBoundary() {
        assertEquals('_', tape.read(3));
    }

    @Test
    void writeInBoundary() {
        tape.write(0, 'c');
        assertEquals('c', tape.read(0));
    }

}