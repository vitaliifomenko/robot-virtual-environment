package com.robotics.virtual.environment.model.script;

import java.util.List;

public record Script(List<String> rawScript) {

    public Script() {
        this(List.of());
    }

}
