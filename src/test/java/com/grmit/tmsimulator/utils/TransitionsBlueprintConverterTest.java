package com.grmit.tmsimulator.utils;

import com.grmit.tmsimulator.model.Transition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransitionsBlueprintConverterTest {

    @Test
    void shouldBuildTransitionListFromStringList() {
        TransitionsBlueprintConverter transitionsBlueprintConverter = new TransitionsBlueprintConverter();
        List<Transition> expected = List.of(
            Transition.of(10, 10, 'a', 'a', 'R'),
            Transition.of(0, 0, 'x', 'x', 'R'),
            Transition.of(0, 0, 'y', 'y', 'R')
        );
        assertEquals(expected, transitionsBlueprintConverter.from(
            List.of(
                "10 a a R 10",
                "0 x x R 0",
                "0 y y R 0"
            )
        ));
    }
}