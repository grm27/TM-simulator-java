package com.grmit.tmsimulator.model;

public record Transition(
    int fromState,
    int toState,
    char read,
    char write,
    char headDir
) {

    public static Transition of(int fromState, int toState, char read, char write, char tapeDir) {
        return new Transition(fromState, toState, read, write, tapeDir);
    }

    public String index() {
        return "" + fromState + read;
    }

    public ProcessingState apply(ProcessingState from, boolean cloneTape) throws IllegalArgumentException {
        if (fromState == from.state() && from.currChar() == read) {
            Tape newConfigTape = cloneTape ? from.tape().clone() : from.tape();
            newConfigTape.write(from.headPosition(), write);
            return new ProcessingState(
                toState,
                moveTapeHead(from.headPosition()),
                from.iteration() + 1,
                newConfigTape
            );
        }
        throw new IllegalArgumentException(String.format("Transition not applicable in configuration %d %c", from.state(), from.currChar()));
    }

    private int moveTapeHead(int currHeadPosition) {
        return switch (headDir) {
            case 'R' -> ++currHeadPosition;
            case 'L' -> --currHeadPosition;
            default -> currHeadPosition;
        };
    }
}
