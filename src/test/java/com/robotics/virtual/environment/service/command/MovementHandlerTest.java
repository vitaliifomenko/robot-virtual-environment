package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.exception.command.MoveCommandOutOfBoundException;
import com.robotics.virtual.environment.model.action.MovementType;
import com.robotics.virtual.environment.model.command.MovementCommand;
import com.robotics.virtual.environment.model.state.robot.Location;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.robotics.virtual.environment.TestDataFactory.getRobotEnvironmentStateWithoutCommands;
import static org.apache.commons.collections4.ListUtils.union;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovementHandlerTest {

    private final MovementHandler movementHandler = new MovementHandler();

    @Test
    public void handleMovementCommandTest() {
        final var command = MovementCommand.builder()
                .actionType(MovementType.FORWARD)
                .steps(1)
                .build();
        final var state = getRobotEnvironmentStateWithoutCommands();
        final var robotState = state.robotState();
        final var expectedState = state.toBuilder().robotState(
                robotState.toBuilder()
                        .commands(union(robotState.commands(), List.of(command)))
                        .location(Location.builder()
                                .xCoordinate(robotState.location().xCoordinate())
                                .yCoordinate(robotState.location().yCoordinate() - command.steps())
                                .build())
                        .build()
        ).build();

        Assertions.assertThat(movementHandler.handle(getRobotEnvironmentStateWithoutCommands(), command))
                .isEqualTo(expectedState);
    }

    @Test
    public void handleMovementCommandThrowErrorTest() {
        final var command = MovementCommand.builder()
                .actionType(MovementType.FORWARD)
                .steps(10)
                .build();
        assertThrows(MoveCommandOutOfBoundException.class,
                () -> movementHandler.handle(getRobotEnvironmentStateWithoutCommands(), command));
    }

}
