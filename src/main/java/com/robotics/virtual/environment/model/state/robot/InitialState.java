package com.robotics.virtual.environment.model.state.robot;

import lombok.Builder;

@Builder
public record InitialState(Location location, Direction direction) {
}
