package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;

public interface CommandHandler<C extends Command> {

    RobotEnvironmentState handle(RobotEnvironmentState state, C command);

}
