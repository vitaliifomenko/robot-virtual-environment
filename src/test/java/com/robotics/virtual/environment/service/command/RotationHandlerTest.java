package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.model.action.RotationType;
import com.robotics.virtual.environment.model.command.RotationCommand;
import com.robotics.virtual.environment.model.state.robot.Direction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.robotics.virtual.environment.TestDataFactory.getRobotEnvironmentStateWithoutCommands;
import static org.apache.commons.collections4.ListUtils.union;
import static org.assertj.core.api.Assertions.assertThat;

public class RotationHandlerTest {

    private final RotationHandler rotationHandler = new RotationHandler();

    private static Stream<Arguments> directions() {
        return Stream.of(
                Arguments.of(RotationType.RIGHT, Direction.EAST),
                Arguments.of(RotationType.LEFT, Direction.WEST),
                Arguments.of(RotationType.TURNAROUND, Direction.SOUTH)
        );
    }

    @ParameterizedTest
    @MethodSource("directions")
    public void handleRotationCommandTest(RotationType rotation, Direction expectedDirection) {
        final var command = RotationCommand.builder()
                .actionType(rotation)
                .build();
        final var state = getRobotEnvironmentStateWithoutCommands();
        final var robotState = state.robotState();
        final var expectedState = state.toBuilder().robotState(
                robotState.toBuilder()
                        .commands(union(robotState.commands(), List.of(command)))
                        .direction(expectedDirection)
                        .build()
        ).build();

        assertThat(rotationHandler.handle(getRobotEnvironmentStateWithoutCommands(), command))
                .isEqualTo(expectedState);
    }

}
