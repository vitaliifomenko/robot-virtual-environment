package com.robotics.virtual.environment.service;

import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;

import java.util.List;
import java.util.Optional;

public interface ControlService {

    Optional<RobotEnvironmentState> handleRobotControl(RobotEnvironmentState state, List<Command> commands);

}
