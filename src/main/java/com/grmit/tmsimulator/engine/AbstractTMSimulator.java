package com.grmit.tmsimulator.engine;

import com.grmit.tmsimulator.storage.TransitionRepository;

import java.util.List;

public abstract class AbstractTMSimulator implements TMSimulator {

    protected final TransitionRepository transitionRepository;
    protected final List<Integer> finalStates;
    protected final int maxIterations;

    public AbstractTMSimulator(TransitionRepository transitionRepository, List<Integer> finalStates, int maxIterations) {
        this.transitionRepository = transitionRepository;
        this.finalStates = finalStates;
        this.maxIterations = maxIterations;
    }

    public List<Integer> getFinalStates() {
        return finalStates;
    }

    public int getMaxIterations() {
        return maxIterations;
    }
}