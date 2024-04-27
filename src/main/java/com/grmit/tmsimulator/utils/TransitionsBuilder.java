package com.grmit.tmsimulator.utils;

import com.grmit.tmsimulator.model.Transition;

import java.util.List;

public class TransitionsBuilder {
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
