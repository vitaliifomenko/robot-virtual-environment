package com.robotics.virtual.environment.model.state.environment;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.IntegerRange;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

@Getter
public class EnvironmentState {

    private final IntegerRange width;
    private final IntegerRange height;

    @Builder
    public EnvironmentState(Integer width, Integer height) {
        this.width = IntegerRange.of(INTEGER_ZERO, width);
        this.height = IntegerRange.of(INTEGER_ZERO, height);
    }

}
