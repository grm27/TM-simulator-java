package com.grmit.tminterepreter.utils;

import com.grmit.tminterepreter.engine.AbstractTMSimulator;
import com.grmit.tminterepreter.engine.TMSimulatorBreadthFirstSearchImpl;
import com.grmit.tminterepreter.storage.HashTableTransitionRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BfsWithHashRepositoryTmSimulatorFactory implements TMSimulatorFactory {

    private final String specificationFileName;
    private final SimpleTransitionsConverter simpleTransitionsConverter;

    public BfsWithHashRepositoryTmSimulatorFactory(String specificationFileName, SimpleTransitionsConverter simpleTransitionsConverter) {
        this.specificationFileName = specificationFileName;
        this.simpleTransitionsConverter = simpleTransitionsConverter;
    }

    @Override
    public AbstractTMSimulator createTMSimulator() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(specificationFileName))) {
            validateFileStart(fileReader);
            List<String> transitions = readTransitions(fileReader);
            List<Integer> acceptanceStates = readAcceptanceStates(fileReader);
            int maxIteration = readMaxIterations(fileReader);
            return createTmSimulator(transitions, acceptanceStates, maxIteration);
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
        List<String> transitions = new ArrayList<>();
        String line;
        while ((line = fileReader.readLine()) != null && !"acc".equals(line)) {
            transitions.add(line);
        }
        if (line == null) {
            throw new IllegalArgumentException("No 'acc' marker found in the specification file");
        }
        return transitions;
    }

    private List<Integer> readAcceptanceStates(BufferedReader fileReader) throws IOException {
        List<Integer> acceptanceStates = new ArrayList<>();
        String line;
        while ((line = fileReader.readLine()) != null && !"max".equals(line)) {
            try {
                acceptanceStates.add(Integer.parseInt(line));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in acceptance states list", e);
            }
        }
        if (line == null) {
            throw new IllegalArgumentException("No 'max' marker found in the specification file");
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

    private TMSimulatorBreadthFirstSearchImpl createTmSimulator(List<String> transitions,
                                                                List<Integer> acceptanceStates,
                                                                int maxIterations) {
        return new TMSimulatorBreadthFirstSearchImpl(
                new HashTableTransitionRepository(simpleTransitionsConverter.from(transitions)),
                acceptanceStates,
                maxIterations
        );
    }

}
