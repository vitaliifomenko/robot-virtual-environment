package com.robotics.virtual.environment.configuration;

import com.robotics.virtual.environment.model.state.robot.Direction;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.IntegerRange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Builder
@ConfigurationProperties("default")
public record DefaultApplicationProperties(EnvironmentConfiguration environment, RobotConfiguration robot) {

    public record EnvironmentConfiguration(Size width, Size height) {

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

        @ConstructorBinding
        public Size(Integer min, Integer max, Integer defaultValue) {
            this.range = IntegerRange.of(min, max);
            this.defaultValue = defaultValue;
        }

    }

}
