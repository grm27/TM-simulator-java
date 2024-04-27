package com.grmit.tmsimulator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TransitionTest {

    @Test
    void shouldMoveHeadOnRightAndChangeState() {
        Tape tape = Tape.of(new char[]{'_', 'a', 'a', '_'});
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                tape
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'R'
        );

        ComputationConfig to = transition.apply(from, false);

        assertNotNull(to);
        assertEquals(1, to.state());
        assertEquals(2, to.headPosition());
        assertEquals(1, to.iteration());
        assertEquals(tape, to.tape());
    }

    @Test
    void shouldMoveHeadOnLeftAndChangeState() {
        Tape tape = Tape.of(new char[]{'_', 'a', 'a', '_'});
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                tape
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'L'
        );

        ComputationConfig to = transition.apply(from, false);

        assertNotNull(to);
        assertEquals(1, to.state());
        assertEquals(0, to.headPosition());
        assertEquals(1, to.iteration());
        assertEquals(tape, to.tape());
    }

    @Test
    void shouldNotMoveHeadAndChangeState() {
        Tape tape = Tape.of(new char[]{'_', 'a', 'a', '_'});
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                tape
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'S'
        );

        ComputationConfig to = transition.apply(from, false);

        assertNotNull(to);
        assertEquals(1, to.state());
        assertEquals(1, to.headPosition());
        assertEquals(1, to.iteration());
        assertEquals(tape, to.tape());
    }

    @Test
    void shouldRemainInTheSameStateOnTheSamePosition() {
        Tape tape = Tape.of(new char[]{'_', 'a', 'a', '_'});
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                tape
        );
        Transition transition = new Transition(
                0, 0, 'a', 'a', 'S'
        );

        ComputationConfig to = transition.apply(from, false);

        assertNotNull(to);
        assertEquals(0, to.state());
        assertEquals(1, to.headPosition());
        assertEquals(1, to.iteration());
        assertEquals(tape, to.tape());
    }

    @Test
    void shouldReturnNullWhenNotApplicableOnState() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                Tape.of(new char[]{'_', 'a', 'a', '_'})
        );
        Transition transition = new Transition(
                1, 1, 'a', 'b', 'S'
        );
        assertNull(transition.apply(from, false));
    }

    @Test
    void shouldReturnNullWhenNotApplicableOnCurrChar() {
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                Tape.of(new char[]{'_', 'a', 'a', '_'})
        );
        Transition transition = new Transition(
                0, 1, 'b', 'c', 'S'
        );
        assertNull(transition.apply(from, false));
    }

    @Test
    void shouldReturnTapeCopyWhenCloneFlagIsTrue() {
        Tape tape = Tape.of(new char[]{'_', 'a', 'a', '_'});
        ComputationConfig from = new ComputationConfig(
                0,
                1,
                0,
                tape
        );
        Transition transition = new Transition(
                0, 1, 'a', 'b', 'R'
        );

        ComputationConfig to = transition.apply(from, true);

        assertNotNull(to);
        assertEquals(1, to.state());
        assertEquals(2, to.headPosition());
        assertEquals(1, to.iteration());
        assertNotEquals(tape, to.tape());
    }
}
