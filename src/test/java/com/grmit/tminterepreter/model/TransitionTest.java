package com.grmit.tminterepreter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class TransitionTest {

    @Test
    void moveHeadOnRightTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                Tape.of(new char[]{'_', 'a', 'a', '_'})
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'R'
        );
        ComputationConfig to = new ComputationConfig(
                1,
                2,
                1,
                Tape.of(new char[]{'_', 'b', 'a', '_'})
        );

        assertConfigurationEquals(to, transition.apply(from));
    }

    @Test
    void moveHeadOnLeftTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                Tape.of(new char[]{'_', 'a', 'a', '_'})
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'L'
        );
        ComputationConfig to = new ComputationConfig(
                1,
                0,
                1,
                Tape.of(new char[]{'_', 'b', 'a', '_'})
        );

        assertConfigurationEquals(to, transition.apply(from));
    }

    @Test
    void steadyStateTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                Tape.of(new char[]{'_', 'a', 'a', '_'})
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'S'
        );
        ComputationConfig to = new ComputationConfig(
                1,
                1,
                1,
                Tape.of(new char[]{'_', 'b', 'a', '_'})
        );

        assertConfigurationEquals(to, transition.apply(from));
    }

    @Test
    void notApplicableOnCurrStateTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                Tape.of(new char[]{'_', 'a', 'a', '_'})
        );
        Transition transition = new Transition(
                1, 1, 'a', 'b', 'S'
        );
        assertNull(transition.apply(from));
    }

    @Test
    void notApplicableOnCurrCharTransition() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                Tape.of(new char[]{'_', 'a', 'a', '_'})
        );
        Transition transition = new Transition(
                0, 1, 'b', 'c', 'S'
        );
        assertNull(transition.apply(from));
    }

    private void assertConfigurationEquals(ComputationConfig expected, ComputationConfig actual) {
        if (expected == null && actual == null)
            return;

        assertFalse(expected == null || actual == null,
                "Comparing ending and non ending configuration");
        assertEquals(expected.headPosition(), actual.headPosition(),
                "Head positions do not match");
        assertEquals(expected.state(), actual.state(),
                "States do not match");
        assertEquals(expected.tape(), actual.tape(),
                "Tapes are not in the same configuration");
        assertEquals(expected.iteration(), actual.iteration(),
                "Configurations have different iterations");
    }
}