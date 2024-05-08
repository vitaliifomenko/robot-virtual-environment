package com.robotics.virtual.environment.service.script.commandparser;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.script.RawCommand;

public interface CommandParser<A extends ActionType> {

    Command<A> parse(RawCommand rawCommand);

    boolean canParse(RawCommand rawCommand);

}
