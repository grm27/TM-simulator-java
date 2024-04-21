package com.grmit.tminterepreter.storage;

import com.grmit.tminterepreter.model.Transition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.groupingBy;

public class HashTableTransitionRepository implements TransitionRepository {

    private final Map<String, List<Transition>> transitionMap;

    public HashTableTransitionRepository(Supplier<Transition[]> transitionsSupplier) {
        transitionMap = Arrays.stream(transitionsSupplier.get()).collect(
                groupingBy(Transition::index)
        );
    }

    @Override
    public List<Transition> get(int state, char curr) {
        return transitionMap.get("" + state + curr);
    }
}
