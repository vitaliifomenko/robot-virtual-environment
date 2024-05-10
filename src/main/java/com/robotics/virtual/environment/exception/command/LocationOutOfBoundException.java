package com.robotics.virtual.environment.exception.command;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationOutOfBoundException extends CommandException {

    private Command<? extends ActionType> command;

}
