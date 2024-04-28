package com.grmit.tmsimulator.model;

public record ProcessingState(
    int state,
    int headPosition,
    int iteration,
    Tape tape
) {

    public char currChar() {
        return tape.read(headPosition);
    }
}
