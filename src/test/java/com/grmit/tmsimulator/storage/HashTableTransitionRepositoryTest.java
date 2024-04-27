package com.grmit.tmsimulator.storage;

import com.grmit.tmsimulator.model.Transition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HashTableTransitionRepositoryTest {

    private static TransitionRepository transitionRepository;

    @BeforeAll
    static void beforeAll() {
        transitionRepository = new HashTableTransitionRepository(
                List.of(
                        Transition.of(0, 1, 'a', 'b', 'R'),
                        Transition.of(0, 1, 'a', 'c', 'R'),
                        Transition.of(1, 1, 'a', 'b', 'R'),
                        Transition.of(1, 1, 'b', 'b', 'R')
                )
        );
    }

    @Test
    void shouldReturnEmptyListInCaseOfMismatch() {
        List<Transition> mismatchOnState = transitionRepository.get(2, 'a');
        List<Transition> mismatchOnChar = transitionRepository.get(0, 'c');
        assertNotNull(mismatchOnState);
        assertNotNull(mismatchOnChar);
        assertEquals(0, mismatchOnState.size());
        assertEquals(0, mismatchOnChar.size());
    }

    @Test
    void shouldReturnMatchingTransitionsWhenMatches() {
        assertEquals(List.of(
                Transition.of(0, 1, 'a', 'b', 'R'),
                Transition.of(0, 1, 'a', 'c', 'R')
        ), transitionRepository.get(0, 'a'));
    }
}