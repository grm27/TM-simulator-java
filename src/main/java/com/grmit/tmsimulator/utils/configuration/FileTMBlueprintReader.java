package com.grmit.tmsimulator.utils.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTMBlueprintReader implements TMBlueprintReader {
    @Override
    public TMBlueprint read(String tmName) {
        try (BufferedReader fileReader =
                 new BufferedReader(new FileReader("src/test/resources/tests/" + tmName + ".txt"))) {
            validateFileStart(fileReader);
            return new TMBlueprint(
                readTransitions(fileReader),
                readAcceptanceStates(fileReader),
                readMaxIterations(fileReader)
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from the specification file: " + e.getMessage(), e);
        }
    }

    private void validateFileStart(BufferedReader fileReader) throws IOException {
        if (!"tr".equals(fileReader.readLine())) {
            throw new IllegalArgumentException("The specification file does not start with 'tr'");
        }
    }

    private List<String> readTransitions(BufferedReader fileReader) throws IOException {
        return readFileLines(fileReader, "acc");
    }

    private List<Integer> readAcceptanceStates(BufferedReader fileReader) throws IOException {
        return readFileLines(fileReader, "max").stream().map(Integer::parseInt).toList();
    }

    private static List<String> readFileLines(BufferedReader fileReader, String endingMarker) throws IOException {
        List<String> acceptanceStates = new ArrayList<>();
        String line;
        while ((line = fileReader.readLine()) != null && !endingMarker.equals(line)) {
            acceptanceStates.add(line);
        }
        if (line == null) {
            throw new IllegalArgumentException("No '" + endingMarker + "' marker found in the specification file");
        }
        return acceptanceStates;
    }

    private int readMaxIterations(BufferedReader fileReader) throws IOException {
        String line = fileReader.readLine();
        if (line == null) {
            throw new IllegalArgumentException("Expected max iteration count missing from the specification file");
        }
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for max iteration count", e);
        }
    }
}
