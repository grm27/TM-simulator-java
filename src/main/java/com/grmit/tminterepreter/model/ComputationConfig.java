package com.grmit.tminterepreter.model;

public record ComputationConfig(
        int state,
        int headPosition,
        Tape tape
) {
}
