package com.grmit.tmsimulator.utils.configuration;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileTMBlueprintReaderTest {

    private final FileTMBlueprintReader fileTMBlueprintReader = new FileTMBlueprintReader();

    @Test
    void correctSample() {
        TMBlueprint expected = new TMBlueprint(
                List.of("1 a a R 1", "0 x x R 0"),
                List.of(0, 1),
                2
        );
        TMBlueprint actual = fileTMBlueprintReader.read("sample");
        assertEquals(expected, actual);
    }

    @Test
    void invalidStart() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> fileTMBlueprintReader.read("invalidspecs/invalidStart"));
        assertEquals("The specification file does not start with 'tr'", thrown.getMessage());
    }

    @Test
    void acceptanceStateNotPresent() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> fileTMBlueprintReader.read("invalidspecs/acceptanceNotPresent"));
        assertEquals("No 'acc' marker found in the specification file", thrown.getMessage());
    }

    @Test
    void maxIterationsNotPresent() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> fileTMBlueprintReader.read("invalidspecs/maxIterationNotPresent"));
        assertEquals("No 'max' marker found in the specification file", thrown.getMessage());
    }
}