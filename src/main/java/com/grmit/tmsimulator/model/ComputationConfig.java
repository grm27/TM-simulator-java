package com.grmit.tmsimulator.model;

public record ComputationConfig(
        int state,
        int headPosition,
        int iteration,
        Tape tape
) {
}
