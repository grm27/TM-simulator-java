package com.grmit.tminterepreter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TapeTest {

    private final char[] initTape = new char[]{'a', 'b'};
    private final Tape tape = new Tape(initTape);

    @Test
    void readInBoundary() {
        assertEquals('a', tape.read(0));
    }


}