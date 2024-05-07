package com.robotics.virtual.environment.model.state.robot;

import com.robotics.virtual.environment.model.command.Command;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record RobotState(Location location, Direction direction, List<Command> commands) {
}
