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

    public ComputationConfig apply(ComputationConfig from, boolean cloneTape) {
        if (fromState == from.state() && from.tape().read(from.headPosition()) == read) {
            from.tape().write(from.headPosition(), write);
            Tape newConfigTape = cloneTape ? from.tape().clone() : from.tape();
            return new ComputationConfig(
                    toState,
                    moveTapeHead(from.headPosition()),
                    from.iteration() + 1,
                    newConfigTape
            );
        }
        return null;
    }

    private int moveTapeHead(int currHeadPosition) {
        return switch (headDir) {
            case 'R' -> ++currHeadPosition;
            case 'L' -> --currHeadPosition;
            default -> currHeadPosition;
        };
    }
}
