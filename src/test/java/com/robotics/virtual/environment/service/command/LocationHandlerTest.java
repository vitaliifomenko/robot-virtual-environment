package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.exception.command.LocationOutOfBoundException;
import com.robotics.virtual.environment.model.action.LocationType;
import com.robotics.virtual.environment.model.command.LocationCommand;
import com.robotics.virtual.environment.model.state.environment.EnvironmentState;
import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.Location;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.robotics.virtual.environment.TestDataFactory.getRobotEnvironmentStateWithoutCommands;
import static org.apache.commons.collections4.ListUtils.union;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocationHandlerTest {

    private final LocationHandler locationHandler = new LocationHandler();

    @Test
    public void handleLocationCommandTest() {
        final var command = LocationCommand.builder()
                .actionType(LocationType.POSITION)
                .direction(Direction.EAST)
                .location(
                        Location.builder()
                                .xCoordinate(1)
                                .yCoordinate(3)
                                .build()
                )
                .build();
        final var state = getRobotEnvironmentStateWithoutCommands();
        final var robotState = state.robotState();
        final var expectedState = state.toBuilder().robotState(
                robotState.toBuilder()
                        .commands(union(robotState.commands(), List.of(command)))
                        .location(command.location())
                        .direction(command.direction())
                        .build()
        ).build();

        Assertions.assertThat(locationHandler.handle(getRobotEnvironmentStateWithoutCommands(), command))
                .isEqualTo(expectedState);
    }


    @Test
    public void handleLocationCommandThrowErrorTest() {
        final var command = LocationCommand.builder()
                .actionType(LocationType.POSITION)
                .direction(Direction.EAST)
                .location(
                        Location.builder()
                                .xCoordinate(1)
                                .yCoordinate(10)
                                .build()
                )
                .build();
        final var state = getRobotEnvironmentStateWithoutCommands();
        final var newState = state.toBuilder().environmentState(
                EnvironmentState.builder()
                        .width(5)
                        .height(5)
                        .build()
        ).build();
        assertThrows(LocationOutOfBoundException.class,
                () -> locationHandler.handle(newState, command));
    }

}
