package com.grmit.tminterepreter.model;

public record Transition(
        int fromState,
        int toState,
        char read,
        char write,
        char tapeDir
) {

    public ComputationConfig apply(ComputationConfig from) {
        if (fromState == from.state() && from.tape().read(from.headPosition()) == read)
            return new ComputationConfig(
                    toState,
                    moveTapeHead(from.headPosition()),
                    writeOnTape(from.tape(), from.headPosition(), write)
            );
        return null;
    }

    private int moveTapeHead(int currHeadPosition) {
        return switch (tapeDir) {
            case 'R' -> ++currHeadPosition;
            case 'L' -> --currHeadPosition;
            default -> currHeadPosition;
        };
    }

    private Tape writeOnTape(Tape tape, int headPos, char write) {
        tape.write(headPos, write);
        return tape;
    }
}
