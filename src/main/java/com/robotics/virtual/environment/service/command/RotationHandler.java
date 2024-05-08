package com.robotics.virtual.environment.service.command;

import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.action.RotationType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.RotationCommand;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.model.state.robot.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.union;

@Service
public class RotationHandler implements CommandHandler<RotationCommand> {

    @Override
    public RobotEnvironmentState handle(RobotEnvironmentState state, Command<? extends ActionType> command) {
        final var rotationCommand = (RotationCommand) command;
        final var robotState = state.robotState();
        final var newState = robotState.toBuilder()
                .commands(union(robotState.commands(), List.of(rotationCommand)))
                .direction(rotationToDirection(robotState.direction(), rotationCommand.actionType()))
                .build();

        return state.toBuilder()
                .robotState(newState)
                .build();
    }

    private Direction rotationToDirection(Direction direction, RotationType rotationType) {
        final var newDirection = switch (rotationType) {
            case RIGHT -> direction.ordinal() + 1;
            case LEFT -> direction.ordinal() - 1;
            case TURNAROUND -> direction.ordinal() + 2;
        };
        final var directions = Direction.values();
        return directions[((newDirection + directions.length) % directions.length)];
    }

    @Override
    public Class<RotationCommand> getHandleCommand() {
        return RotationCommand.class;
    }

}
