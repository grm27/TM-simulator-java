package com.grmit.tmsimulator.core;

import com.grmit.tmsimulator.utils.BfsTMSimulatorFactory;
import com.grmit.tmsimulator.utils.TransitionsBlueprintConverter;
import com.grmit.tmsimulator.utils.configuration.FileTMBlueprintReader;
import com.grmit.tmsimulator.utils.configuration.TMBlueprint;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TMSimulatorBreadthFirstSearchImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TMSimulatorBreadthFirstSearchImplTest.class);

    @ParameterizedTest
    @CsvSource({
        "increasingStuff",
        "dontGetLost",
        "fancyLoops",
        "mindYourLeft",
        "toCOrNotToC",
        "UnionStuck"
    })
    void shouldProduceExpectedResults(String model) throws IOException {
        TMSimulator tmSimulator = buildTmSimulator(model);
        LOGGER.info("Starting {}", model);
        File outputFile = new File("src/test/resources/tests/temp.txt");
        processTest(model, tmSimulator, outputFile);
        assertFileEquals(
            new File(String.format("src/test/resources/tests/output%s.txt", model)),
            outputFile
        );
    }

    private void processTest(String model, TMSimulator tmSimulator, File outputFile) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(String.format("src/test/resources/tests/%s.txt", model)));
             BufferedWriter resultWriter = new BufferedWriter(new FileWriter(outputFile))) {
            readInputLines(fileReader).forEach(
                input -> writeResult(tmSimulator, resultWriter, input)
            );
        }
    }

    private void writeResult(TMSimulator tmSimulator, BufferedWriter writer, String input) {
        try {
            writer.write(tmSimulator.isStringAccepted(input.toCharArray()));
            writer.newLine();
        } catch (IOException e) {
            LOGGER.error("Error writing to result file", e);
            throw new UncheckedIOException(e);
        }
    }

    private static TMSimulator buildTmSimulator(String model) {
        TMBlueprint tmBlueprint = new FileTMBlueprintReader().read(model);
        BfsTMSimulatorFactory bfsTMSimulatorFactory = new BfsTMSimulatorFactory(tmBlueprint, new TransitionsBlueprintConverter());
        return bfsTMSimulatorFactory.createTMSimulator();
    }

    private static List<String> readInputLines(BufferedReader fileReader) throws IOException {
        List<String> inputStrings = new ArrayList<>();
        String line;
        boolean startCollecting = false;

        while ((line = fileReader.readLine()) != null) {
            if (startCollecting) {
                inputStrings.add(line);
            } else if ("run".equals(line)) {
                startCollecting = true; // Start collecting lines after "run" is found
            }
        }

        return inputStrings;
    }

    private void assertFileEquals(File expected, File actual) throws IOException {
        assertEquals(
            FileUtils.readFileToString(expected, StandardCharsets.UTF_8),
            FileUtils.readFileToString(actual, StandardCharsets.UTF_8),
            "Unexpected machine outcome"
        );
    }
}