package com.grmit.tminterepreter.egine;

import com.grmit.tminterepreter.storage.TransitionRepository;

public class TMSimulatorBreadthFirstSearchImpl implements TMSimulator {

    private final TransitionRepository transitionRepository;
    private final int maxIterations;

    public TMSimulatorBreadthFirstSearchImpl(TransitionRepository transitionRepository, int maxIterations) {
        this.transitionRepository = transitionRepository;
        this.maxIterations = maxIterations;
    }

    @Override
    public char isStringAccepted(char[] inputString) {
        return 0;
    }
}
