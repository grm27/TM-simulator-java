package com.grmit.tminterepreter.model;

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

    public ComputationConfig apply(ComputationConfig from) {
        if (fromState == from.state() && from.tape().read(from.headPosition()) == read)
            return new ComputationConfig(
                    toState,
                    moveTapeHead(from.headPosition()),
                    from.iteration() + 1,
                    writeOnTape(from.tape(), from.headPosition(), write)
            );
        return null;
    }

    private int moveTapeHead(int currHeadPosition) {
        return switch (headDir) {
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
