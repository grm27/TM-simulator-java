package com.grmit.tmsimulator.utils.configuration;

import java.util.List;

public record TMBlueprint(
    List<String> transitions,
    List<Integer> acceptingStates,
    int maxIteration
) {
}
