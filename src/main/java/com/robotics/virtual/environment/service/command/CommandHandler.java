package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;

public interface CommandHandler<C extends Command<? extends ActionType>> {

    RobotEnvironmentState handle(RobotEnvironmentState state, Command<? extends ActionType> command);

    Class<C> getHandleCommand();

}
