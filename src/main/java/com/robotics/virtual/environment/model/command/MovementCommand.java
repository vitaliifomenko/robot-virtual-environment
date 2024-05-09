package com.robotics.virtual.environment.model.command;

import com.robotics.virtual.environment.model.action.MovementType;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

@Builder
public record MovementCommand(MovementType actionType, Integer steps) implements Command<MovementType> {

    @Override
    public String toString() {
        return String.join(StringUtils.SPACE, actionType.name(), String.valueOf(steps));
    }

}
