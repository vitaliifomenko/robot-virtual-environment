package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.exception.LocationOutOfBoundException;
import com.robotics.virtual.environment.model.command.MovementCommand;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.model.state.robot.Location;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.union;

@Service
public class MovementHandler implements CommandHandler<MovementCommand> {

    @Override
    public RobotEnvironmentState handle(RobotEnvironmentState state, MovementCommand command) {
        final var robotState = state.robotState();
        final var newState = robotState.toBuilder()
                .commands(union(robotState.commands(), List.of(command)))
                .location(getLocation(state, command))
                .build();

        return state.toBuilder()
                .robotState(newState)
                .build();
    }

    private Location getLocation(RobotEnvironmentState state, MovementCommand command) {
        final var currentLocation = state.robotState().location();
        final var currentDirection = state.robotState().direction();
        final var steps = command.steps();

        final var newLocation = switch (currentDirection) {
            case NORTH -> currentLocation.withYCoordinate(currentLocation.yCoordinate() - steps);
            case SOUTH -> currentLocation.withYCoordinate(currentLocation.yCoordinate() + steps);
            case EAST -> currentLocation.withXCoordinate(currentLocation.xCoordinate() + steps);
            case WEST -> currentLocation.withXCoordinate(currentLocation.xCoordinate() - steps);
        };

        if (!state.environmentState().width().contains(newLocation.xCoordinate()) ||
                !state.environmentState().height().contains(newLocation.yCoordinate())) {
            throw new LocationOutOfBoundException(command);
        }
        return newLocation;
    }

}