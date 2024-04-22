package com.grmit.tmsimulator.storage;

import com.grmit.tmsimulator.model.Transition;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class HashTableTransitionRepository implements TransitionRepository {

    private final Map<String, List<Transition>> transitionMap;

    public HashTableTransitionRepository(List<Transition> transitionsSupplier) {
        transitionMap = transitionsSupplier.stream().collect(
                groupingBy(Transition::index)
        );
    }

    @Override
    public List<Transition> get(int state, char curr) {
        return transitionMap.getOrDefault("" + state + curr, Collections.emptyList());
    }
}
