package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.model.action.WaitType;
import com.robotics.virtual.environment.model.command.WaitCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.robotics.virtual.environment.TestDataFactory.getRobotEnvironmentStateWithoutCommands;
import static org.apache.commons.collections4.ListUtils.union;
import static org.assertj.core.api.Assertions.assertThat;

public class WaitHandlerTest {

    private final WaitHandler waitHandler = new WaitHandler();

    @Test
    public void handleWaitCommandTest() {
        final var command = WaitCommand.builder()
                .actionType(WaitType.WAIT)
                .build();
        final var state = getRobotEnvironmentStateWithoutCommands();
        final var robotState = state.robotState();
        final var expectedState = state.toBuilder().robotState(
                robotState.toBuilder()
                        .commands(union(robotState.commands(), List.of(command)))
                        .build()
        ).build();

        assertThat(waitHandler.handle(getRobotEnvironmentStateWithoutCommands(), command))
                .isEqualTo(expectedState);
    }

}
