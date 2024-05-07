package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.model.command.LocationCommand;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.union;

@Service
public class LocationHandler implements CommandHandler<LocationCommand> {

    @Override
    public RobotEnvironmentState handle(RobotEnvironmentState state, LocationCommand command) {
        final var robotState = state.robotState();
        final var newState = robotState.toBuilder()
                .commands(union(robotState.commands(), List.of(command)))
                .location(command.location())
                .direction(command.direction())
                .build();

        return state.toBuilder()
                .robotState(newState)
                .build();
    }

}
