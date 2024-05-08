package com.robotics.virtual.environment.model.state.robot;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
public record RobotState(Location location, Direction direction, List<Command<? extends ActionType>> commands) {

    @Builder(builderMethodName = "defaultBuilder", builderClassName = "RobotStateDefaultBuilder")
    public RobotState(Location location, Direction direction) {
        this(location, direction, new ArrayList<>());
    }
}
