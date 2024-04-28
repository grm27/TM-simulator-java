package com.grmit.tmsimulator.utils;

import com.grmit.tmsimulator.model.Transition;

import java.util.List;

public class TransitionsBlueprintConverter {
    public List<Transition> from(List<String> transitions) {
        return transitions.stream().map(declaration -> {
            String[] tokens = declaration.split(" ");
            return Transition.of(
                Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[4]),
                tokens[1].charAt(0),
                tokens[2].charAt(0),
                tokens[3].charAt(0)
            );
        }).toList();
    }
}
