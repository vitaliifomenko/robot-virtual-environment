package com.robotics.virtual.environment.model.state.robot;

import lombok.Builder;
import lombok.With;

@Builder(toBuilder = true)
@With
public record Location(Integer xCoordinate, Integer yCoordinate) {

}
