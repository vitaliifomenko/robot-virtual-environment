package com.robotics.virtual.environment.configuration;

import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.Location;
import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties("default")
public record DefaultApplicationProperties(Environment environment, Robot robot) {

    public record Environment(Integer width, Integer height) {
    }

    public record Robot(Location location, Direction direction) {

    }

}
