package com.robotics.virtual.environment.model.state;

import com.robotics.virtual.environment.model.state.environment.EnvironmentState;
import com.robotics.virtual.environment.model.state.robot.RobotState;
import lombok.Builder;

@Builder(toBuilder = true)
public record RobotEnvironmentState(RobotState robotState, EnvironmentState environmentState) {
}
