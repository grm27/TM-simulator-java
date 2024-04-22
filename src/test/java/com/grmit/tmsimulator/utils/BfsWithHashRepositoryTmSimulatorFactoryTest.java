package com.grmit.tmsimulator.utils;

import com.grmit.tmsimulator.engine.AbstractTMSimulator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BfsWithHashRepositoryTmSimulatorFactoryTest {

    private final SimpleTransitionsConverter simpleTransitionsConverter = Mockito.mock(SimpleTransitionsConverter.class);
    private final BfsWithHashRepositoryTmSimulatorFactory fileTMSimulatorFactory = new BfsWithHashRepositoryTmSimulatorFactory(
            "src/test/resources/tests/sample_1.txt",
            simpleTransitionsConverter
    );

    @Test
    void shouldCreateSimulatorFromFile() {
        when(simpleTransitionsConverter.from(anyList())).thenReturn(Collections.emptyList());

        AbstractTMSimulator abstractTMSimulator = fileTMSimulatorFactory.createTMSimulator();

        assertEquals(List.of(2, 6), abstractTMSimulator.getFinalStates());
        assertEquals(50, abstractTMSimulator.getMaxIterations());
        verify(simpleTransitionsConverter).from(eq(
                        List.of(
                                "0 a a R 1",
                                "1 a a R 1"
                        )
                )
        );
    }

}