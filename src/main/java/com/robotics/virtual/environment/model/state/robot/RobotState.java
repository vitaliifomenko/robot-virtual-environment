package com.robotics.virtual.environment.model.state.robot;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Builder(toBuilder = true)
public record RobotState(InitialState initialState, Location location, Direction direction,
                         List<Command<? extends ActionType>> commands) {

    @Builder(builderMethodName = "defaultBuilder", builderClassName = "RobotStateDefaultBuilder")
    public RobotState(Location location, Direction direction) {
        this(new InitialState(location, direction), location, direction, new ArrayList<>());
    }

    public String toStringCommands() {
        return emptyIfNull(commands).stream().map(Object::toString).collect(joining(StringUtils.LF));
    }

}
