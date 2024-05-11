package com.robotics.virtual.environment.service;

import com.robotics.virtual.environment.exception.command.CommandException;
import com.robotics.virtual.environment.exception.command.CommandLimitExceededException;
import com.robotics.virtual.environment.exception.parser.CommandParseException;
import com.robotics.virtual.environment.model.script.RawScript;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;

public interface ControlService {

    /**
     * Returns the new {@link RobotEnvironmentState} as result of execution of the specified {@link RawScript}.
     * If the input script is empty returns the default state.
     *
     * @param script the {@link RawScript} to execute
     * @return the new {@link RobotEnvironmentState}
     * @throws CommandParseException         if an error occurs during parsing
     * @throws CommandException              if an error occurs during command execution
     * @throws CommandLimitExceededException if input script contains more then max commands
     */
    RobotEnvironmentState handleRobotControl(RawScript script);

}
