package com.grmit.tminterepreter.model;

import java.util.Arrays;

public record Transition(
        int fromState,
        int toState,
        char read,
        char write,
        char tapeDir
) {

    public ComputationStep apply(ComputationStep from) {
        if (fromState == from.state() && from.tape()[from.headPosition()] == read)
            return new ComputationStep(
                    toState,
                    moveTapeHead(from.headPosition()),
                    writeOnTape(from.tape(), from.headPosition(), write)
            );
        return null;
    }

    private int moveTapeHead(int currHeadPosition) {
        return switch (tapeDir) {
            case 'R' -> currHeadPosition++;
            case 'L' -> currHeadPosition--;
            default -> currHeadPosition;
        };
    }

    private char[] writeOnTape(char[] tape, int headPos, char write) {
        char[] newTape = Arrays.copyOf(tape, tape.length);
        newTape[headPos] = write;
        return newTape;
    }
}
