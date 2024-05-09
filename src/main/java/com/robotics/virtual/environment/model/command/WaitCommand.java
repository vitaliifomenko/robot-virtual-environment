package com.robotics.virtual.environment.model.command;

import com.robotics.virtual.environment.model.action.WaitType;
import lombok.Builder;

@Builder
public record WaitCommand(WaitType actionType) implements Command<WaitType> {

    @Override
    public String toString() {
        return actionType.name();
    }

}
