package com.robotics.virtual.environment.model.environment;

import java.util.Optional;

public record Environment(Optional<Integer> width, Optional<Integer> height) {

    public Environment(Integer width, Integer height) {
        this(Optional.ofNullable(width), Optional.ofNullable(height));
    }

}
