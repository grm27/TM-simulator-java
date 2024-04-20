package com.grmit.tminterepreter.model;

public record ComputationStep(
        int state,
        int headPosition,
        char[] tape
) {
}
