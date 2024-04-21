package com.grmit.tminterepreter.engine;

import com.grmit.tminterepreter.storage.TransitionRepository;

import java.util.List;

public class TMSimulatorBreadthFirstSearchImpl extends AbstractTMSimulator {

    public TMSimulatorBreadthFirstSearchImpl(TransitionRepository transitionRepository, List<Integer> finalStates, int maxIterations) {
        super(transitionRepository, finalStates, maxIterations);
    }

    @Override
    public char isStringAccepted(char[] inputString) {
        return 0;
    }
}
