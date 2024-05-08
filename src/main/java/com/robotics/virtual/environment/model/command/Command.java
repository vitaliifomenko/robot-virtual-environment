package com.robotics.virtual.environment.model.command;

import com.robotics.virtual.environment.model.action.ActionType;

public interface Command<A extends ActionType> {
    A actionType();
}
