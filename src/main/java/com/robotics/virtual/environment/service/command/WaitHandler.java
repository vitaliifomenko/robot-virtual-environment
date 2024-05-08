package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.WaitCommand;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.union;

@Service
public class WaitHandler implements CommandHandler<WaitCommand> {

    @Override
    public RobotEnvironmentState handle(RobotEnvironmentState state, Command<? extends ActionType> command) {
        final var newState = state.robotState().toBuilder()
                .commands(union(state.robotState().commands(), List.of(command)))
                .build();

        return state.toBuilder()
                .robotState(newState)
                .build();
    }

    @Override
    public Class<WaitCommand> getHandleCommand() {
        return WaitCommand.class;
    }

}
