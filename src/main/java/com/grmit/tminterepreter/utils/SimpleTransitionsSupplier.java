package com.grmit.tminterepreter.utils;

import com.grmit.tminterepreter.model.Transition;

import java.util.List;
import java.util.function.Supplier;

public class SimpleTransitionsSupplier implements Supplier<List<Transition>> {

    private final List<String> transitions;

    public SimpleTransitionsSupplier(List<String> transitions) {
        this.transitions = transitions;
    }

    @Override
    public List<Transition> get() {
        return transitions.stream().map(declaration -> {
            char[] tokens = declaration.toCharArray();
            return Transition.of(
                    Character.getNumericValue(tokens[0]),
                    Character.getNumericValue(tokens[8]),
                    tokens[2],
                    tokens[4],
                    tokens[6]);
        }).toList();
    }
}
