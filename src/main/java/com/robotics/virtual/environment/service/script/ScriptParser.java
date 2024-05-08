package com.robotics.virtual.environment.service.script;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.script.Script;

import java.util.List;

public interface ScriptParser {

    List<Command<? extends ActionType>> parseScript(Script script);

}
