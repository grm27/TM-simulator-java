package com.grmit.tminterepreter.model;

public record ComputationConfig(
        int state,
        int headPosition,
        char[] tape
) {
}
