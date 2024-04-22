package com.grmit.tmsimulator.storage;

import com.grmit.tmsimulator.model.Transition;

import java.util.List;

public interface TransitionRepository {
    List<Transition> get(int state, char curr);
}
