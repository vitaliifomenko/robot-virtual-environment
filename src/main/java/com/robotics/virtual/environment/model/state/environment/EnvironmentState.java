package com.robotics.virtual.environment.model.state.environment;

import lombok.Builder;
import org.apache.commons.lang3.IntegerRange;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

@Builder
public record EnvironmentState(IntegerRange width, IntegerRange height) {

    public EnvironmentState(Integer width, Integer height) {
        this(IntegerRange.of(INTEGER_ZERO, width), IntegerRange.of(INTEGER_ZERO, height));
    }

}
