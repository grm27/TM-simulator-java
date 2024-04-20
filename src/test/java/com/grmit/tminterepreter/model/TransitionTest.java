package com.grmit.tminterepreter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransitionTest {

    @Test
    void inBoundaryRightTransition() {
        Transition transition = new Transition(
                0, 0, 'a', 'b', 'R'
        );
        ComputationStep from = new ComputationStep(
                0,
                0,
                new char[]{'a', 'a', 'b', 'b'}
        );

        ComputationStep to = new ComputationStep(
                0,
                0,
                new char[]{'b', 'a', 'b', 'b'}
        );

        assertConfigurationEquals(to, transition.apply(from));
    }

    private void assertConfigurationEquals(ComputationStep expected, ComputationStep actual) {
        if (expected == null && actual == null)
            return;

        assertTrue((expected != null && actual != null));
        assertEquals(expected.headPosition(), actual.headPosition());
        assertEquals(expected.state(), actual.state());
        assertArrayEquals(expected.tape(), actual.tape());
    }
}