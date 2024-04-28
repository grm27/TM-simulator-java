package com.grmit.tmsimulator.core;

import com.grmit.tmsimulator.model.ProcessingState;
import com.grmit.tmsimulator.model.Tape;
import com.grmit.tmsimulator.model.Transition;
import com.grmit.tmsimulator.storage.TransitionRepository;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class TMSimulatorBreadthFirstSearchImpl extends AbstractTMSimulator {

    private final Queue<ProcessingState> toBeChecked = new ArrayDeque<>();

    public TMSimulatorBreadthFirstSearchImpl(TransitionRepository transitionRepository, List<Integer> finalStates, int maxIterations) {
        super(transitionRepository, finalStates, maxIterations);
    }

    @Override
    public char isStringAccepted(char[] inputString) {
        ProcessingState initConfig = new ProcessingState(
            0, 0, 0, Tape.of(inputString)
        );

        toBeChecked.add(initConfig);
        boolean undefinedBranch = false;
        ProcessingState curr;
        while (!toBeChecked.isEmpty()) {
            curr = toBeChecked.poll();
            List<Transition> applicableTransition = transitionRepository.get(
                curr.state(),
                curr.currChar()
            );
            if (acceptingStates.contains(curr.state()) && applicableTransition.isEmpty()) {
                toBeChecked.clear();
                return '1';
            }
            for (Transition transition : applicableTransition) {
                ProcessingState processingState = transition.apply(curr, applicableTransition.size() > 1);
                if (processingState.iteration() <= maxIterations) {
                    toBeChecked.add(processingState);
                } else undefinedBranch = true;
            }
        }

        return undefinedBranch ? 'U' : '0';
    }
}
