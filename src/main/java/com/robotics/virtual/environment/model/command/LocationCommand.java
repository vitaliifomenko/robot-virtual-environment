package com.robotics.virtual.environment.model.command;

import com.robotics.virtual.environment.model.action.LocationType;
import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.Location;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

@Builder
public record LocationCommand(LocationType actionType, Direction direction,
                              Location location) implements Command<LocationType> {

    @Override
    public String toString() {
        return String.join(StringUtils.SPACE, actionType.name(),
                String.valueOf(location.xCoordinate()), String.valueOf(location.yCoordinate()), direction.name());
    }

}
