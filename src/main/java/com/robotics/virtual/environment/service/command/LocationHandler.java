package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.exception.LocationOutOfBoundException;
import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.LocationCommand;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.union;

@Service
public class LocationHandler implements CommandHandler<LocationCommand> {

    @Override
    public RobotEnvironmentState handle(RobotEnvironmentState state, Command<? extends ActionType> command) {
        final var locationCommand = (LocationCommand) command;
        if (!state.environmentState().getWidth().contains(locationCommand.location().xCoordinate()) ||
                !state.environmentState().getHeight().contains(locationCommand.location().yCoordinate())) {
            throw new LocationOutOfBoundException(command);
        }

        final var robotState = state.robotState();
        final var newState = robotState.toBuilder()
                .commands(union(robotState.commands(), List.of(locationCommand)))
                .location(locationCommand.location())
                .direction(locationCommand.direction())
                .build();

        return state.toBuilder()
                .robotState(newState)
                .build();
    }

    @Override
    public Class<LocationCommand> getHandleCommand() {
        return LocationCommand.class;
    }

}
