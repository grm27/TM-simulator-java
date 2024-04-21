package com.grmit.tminterepreter.storage;

import com.grmit.tminterepreter.model.Transition;

import java.util.List;

public interface TransitionRepository {
    List<Transition> get(int state, char curr);
}
