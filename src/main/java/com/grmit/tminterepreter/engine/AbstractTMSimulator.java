package com.grmit.tminterepreter.engine;

import com.grmit.tminterepreter.storage.TransitionRepository;

import java.util.List;

public abstract class AbstractTMSimulator implements TMSimulator {

    private final TransitionRepository transitionRepository;
    private final List<Integer> finalStates;
    private final int maxIterations;

    public AbstractTMSimulator(TransitionRepository transitionRepository, List<Integer> finalStates, int maxIterations) {
        this.transitionRepository = transitionRepository;
        this.finalStates = finalStates;
        this.maxIterations = maxIterations;
    }

    public TransitionRepository getTransitionRepository() {
        return transitionRepository;
    }

    public List<Integer> getFinalStates() {
        return finalStates;
    }

    public int getMaxIterations() {
        return maxIterations;
    }
}
