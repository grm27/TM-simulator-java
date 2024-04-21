package com.grmit.tminterepreter.storage;

import com.grmit.tminterepreter.model.Transition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void noTransitionPresent() {
        assertEquals(0, transitionRepository.get(2, 'a').size());
        assertEquals(0, transitionRepository.get(0, 'c').size());
    }

    @Test
    void matchingTransitions() {
        assertEquals(List.of(
                Transition.of(0, 1, 'a', 'b', 'R'),
                Transition.of(0, 1, 'a', 'c', 'R')
        ), transitionRepository.get(0, 'a'));
    }
}