package com.grmit.tmsimulator.utils;

import com.grmit.tmsimulator.model.Transition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleTransitionConverterTest {

    @Test
    void shouldConvertStringListIntoTransitionList() {
        TransitionsBuilder transitionsBuilder = new TransitionsBuilder();
        List<Transition> expected = List.of(
                Transition.of(1, 1, 'a', 'a', 'R'),
                Transition.of(0, 0, 'x', 'x', 'R'),
                Transition.of(0, 0, 'y', 'y', 'R')
        );
        assertEquals(expected, transitionsBuilder.from(
                List.of("1 a a R 1",
                        "0 x x R 0",
                        "0 y y R 0")
        ));
    }
}