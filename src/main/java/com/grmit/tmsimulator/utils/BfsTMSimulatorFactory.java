package com.grmit.tmsimulator.utils;

import com.grmit.tmsimulator.core.TMSimulator;
import com.grmit.tmsimulator.core.TMSimulatorBreadthFirstSearchImpl;
import com.grmit.tmsimulator.model.Transition;
import com.grmit.tmsimulator.storage.HashTableTransitionRepository;
import com.grmit.tmsimulator.utils.configuration.TMBlueprint;

import java.util.List;

public class BfsTMSimulatorFactory implements TMSimulatorFactory {

    private final TMBlueprint tmBlueprint;
    private final TransitionsBlueprintConverter transitionsBlueprintConverter;

    public BfsTMSimulatorFactory(TMBlueprint tmBlueprint, TransitionsBlueprintConverter transitionsBlueprintConverter) {
        this.tmBlueprint = tmBlueprint;
        this.transitionsBlueprintConverter = transitionsBlueprintConverter;
    }

    @Override
    public TMSimulator createTMSimulator() {
        List<Transition> transitions = transitionsBlueprintConverter.from(tmBlueprint.transitions());
        return new TMSimulatorBreadthFirstSearchImpl(
                new HashTableTransitionRepository(transitions),
                tmBlueprint.acceptingStates(),
                tmBlueprint.maxIteration()
        );
    }
}
