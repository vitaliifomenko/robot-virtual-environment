package com.robotics.virtual.environment.model.command;

import com.robotics.virtual.environment.model.action.MovementType;
import lombok.Builder;

@Builder
public record MovementCommand(MovementType actionType, Integer steps) implements Command {

}
