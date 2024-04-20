package com.grmit.tminterepreter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransitionTest {

    @Test
    void inBoundaryRightTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                0,
                new char[]{'a', 'a'}
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'R'
        );
        ComputationConfig to = new ComputationConfig(
                1,
                1,
                new char[]{'b', 'a'}
        );

        assertConfigurationEquals(to, transition.apply(from));
    }

    @Test
    void inBoundaryLeftTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                new char[]{'a', 'a'}
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'L'
        );
        ComputationConfig to = new ComputationConfig(
                1,
                0,
                new char[]{'a', 'b'}
        );

        assertConfigurationEquals(to, transition.apply(from));
    }

    @Test
    void inBoundarySteadyStateTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                0,
                new char[]{'a', 'a'}
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'S'
        );
        ComputationConfig to = new ComputationConfig(
                1,
                0,
                new char[]{'b', 'a'}
        );

        assertConfigurationEquals(to, transition.apply(from));
    }

    private void assertConfigurationEquals(ComputationConfig expected, ComputationConfig actual) {
        if (expected == null && actual == null)
            return;

        assertTrue((expected != null && actual != null),
                "Expected non ending configuration, but the machine actually stopped");
        assertEquals(expected.headPosition(), actual.headPosition(),
                "Head positions do not match");
        assertEquals(expected.state(), actual.state(),
                "States do not match");
        assertArrayEquals(expected.tape(), actual.tape(),
                "Tapes are not in the same configuration");
    }
}