package com.robotics.virtual.environment.configuration;

import com.robotics.virtual.environment.model.state.robot.Direction;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.IntegerRange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

@Builder
@ConfigurationProperties("default")
public record DefaultApplicationProperties(EnvironmentConfiguration environment, RobotConfiguration robot) {

    public record EnvironmentConfiguration(Integer width, Integer height) {

    }

    public record RobotConfiguration(LocationConfiguration location, Direction direction, ScriptConfiguration script) {

        public record LocationConfiguration(Size xCoordinate, Size yCoordinate) {

        }

        public record ScriptConfiguration(Size commands, Size steps) {

        }

    }

    @Getter
    public static class Size {

        private final IntegerRange range;
        private final Integer defaultValue;

        @Builder
        @ConstructorBinding
        public Size(Integer min, Integer max, Integer defaultValue) {
            this.range = IntegerRange.of(defaultIfNull(min, INTEGER_ZERO), max);
            this.defaultValue = defaultValue;
        }

    }

}
