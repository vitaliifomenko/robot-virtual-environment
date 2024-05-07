package com.robotics.virtual.environment.model.command;

import com.robotics.virtual.environment.model.action.LocationType;
import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.Location;
import lombok.Builder;

@Builder
public record LocationCommand(LocationType actionType, Direction direction, Location location) implements Command {

}
