package com.grmit.tmsimulator.core;

import com.grmit.tmsimulator.storage.TransitionRepository;

import java.util.List;

public abstract class AbstractTMSimulator implements TMSimulator {

    protected final TransitionRepository transitionRepository;
    protected final List<Integer> acceptingStates;
    protected final int maxIterations;

    public AbstractTMSimulator(TransitionRepository transitionRepository, List<Integer> acceptingStates, int maxIterations) {
        this.transitionRepository = transitionRepository;
        this.acceptingStates = acceptingStates;
        this.maxIterations = maxIterations;
    }
}
