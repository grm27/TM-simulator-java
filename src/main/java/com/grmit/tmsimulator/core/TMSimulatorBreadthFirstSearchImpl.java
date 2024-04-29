package com.grmit.tmsimulator.core;

import com.grmit.tmsimulator.model.ProcessingState;
import com.grmit.tmsimulator.model.Tape;
import com.grmit.tmsimulator.model.Transition;
import com.grmit.tmsimulator.storage.TransitionRepository;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class TMSimulatorBreadthFirstSearchImpl extends AbstractTMSimulator {

    private final Queue<ProcessingState> explorationQueue = new ArrayDeque<>();

    public TMSimulatorBreadthFirstSearchImpl(TransitionRepository transitionRepository, List<Integer> finalStates, int maxIterations) {
        super(transitionRepository, finalStates, maxIterations);
    }

    @Override
    public char isStringAccepted(char[] inputString) {
        initializeQueue(inputString);
        return runSimulator();
    }

    private void initializeQueue(char[] inputString) {
        ProcessingState initState = new ProcessingState(0, 0, 0, Tape.of(inputString));
        explorationQueue.add(initState);
    }

    private char runSimulator() {
        boolean undefinedBranch = false;
        while (!explorationQueue.isEmpty()) {
            ProcessingState curr = explorationQueue.poll();
            if (checkFinalStates(curr)) {
                explorationQueue.clear();
                return '1';
            }
            List<Transition> applicableTransitions = transitionRepository.get(curr.state(), curr.currChar());
            for (Transition transition : applicableTransitions) {
                ProcessingState nextState = transition.apply(curr, applicableTransitions.size() > 1);
                if (nextState.iteration() <= maxIterations) {
                    explorationQueue.add(nextState);
                } else {
                    undefinedBranch = true;
                }
            }
        }
        return undefinedBranch ? 'U' : '0';
    }

    private boolean checkFinalStates(ProcessingState currentState) {
        return acceptingStates.contains(currentState.state()) && transitionRepository.get(currentState.state(), currentState.currChar()).isEmpty();
    }
}
