package com.grmit.tminterepreter.utils;

import com.grmit.tminterepreter.model.Transition;

import java.util.List;

public class SimpleTransitionsConverter {
    public List<Transition> from(List<String> transitions) {
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
