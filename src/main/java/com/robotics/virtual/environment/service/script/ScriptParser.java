package com.robotics.virtual.environment.service.script;

import com.robotics.virtual.environment.exception.parser.InvalidCommandActionException;
import com.robotics.virtual.environment.exception.parser.InvalidCommandException;
import com.robotics.virtual.environment.exception.parser.NotFoundCommandException;
import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.script.RawScript;

import java.util.List;

public interface ScriptParser {

    /**
     * Returns list of {@link Command} parsed from the specified {@link RawScript}.
     *
     * @param script the {@link RawScript} to parse
     * @return a list of {@link Command} parsed from the {@link RawScript}
     * @throws NotFoundCommandException      if no command found
     * @throws InvalidCommandActionException if no valid command action found
     * @throws InvalidCommandException       if no valid command found
     */
    List<Command<? extends ActionType>> parseScript(RawScript script);

}
