package com.robotics.virtual.environment.model.command;

import com.robotics.virtual.environment.model.action.RotationType;
import lombok.Builder;

@Builder
public record RotationCommand(RotationType actionType) implements Command<RotationType> {

}
