package com.robotics.virtual.environment;

import com.robotics.virtual.environment.model.action.*;
import com.robotics.virtual.environment.model.command.*;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.model.state.environment.EnvironmentState;
import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.InitialState;
import com.robotics.virtual.environment.model.state.robot.Location;
import com.robotics.virtual.environment.model.state.robot.RobotState;

import java.util.List;

public class TestDataFactory {


    public static RobotEnvironmentState getRobotEnvironmentStateWithoutCommands() {
        return getRobotEnvironmentState(List.of());
    }

    public static RobotEnvironmentState getRobotEnvironmentState() {
        return getRobotEnvironmentState(getCommands());
    }

    public static RobotEnvironmentState getRobotEnvironmentState(List<Command<? extends ActionType>> commands) {
        return RobotEnvironmentState.builder()
                .environmentState(EnvironmentState.builder()
                        .width(4)
                        .height(4)
                        .build()
                ).robotState(RobotState.builder()
                        .initialState(
                                InitialState.builder()
                                        .location(Location.builder()
                                                .xCoordinate(1)
                                                .yCoordinate(3)
                                                .build())
                                        .direction(Direction.EAST)
                                        .build()
                        )
                        .location(Location.builder()
                                .xCoordinate(3)
                                .yCoordinate(1)
                                .build())
                        .direction(Direction.NORTH)
                        .commands(commands)
                        .build()
                )
                .build();
    }

    public static List<Command<? extends ActionType>> getCommands() {
        return List.of(
                LocationCommand.builder()
                        .actionType(LocationType.POSITION)
                        .direction(Direction.EAST)
                        .location(
                                Location.builder()
                                        .xCoordinate(1)
                                        .yCoordinate(3)
                                        .build()
                        )
                        .build(),
                MovementCommand.builder()
                        .actionType(MovementType.FORWARD)
                        .steps(3)
                        .build(),
                WaitCommand.builder()
                        .actionType(WaitType.WAIT)
                        .build(),
                RotationCommand.builder()
                        .actionType(RotationType.TURNAROUND)
                        .build(),
                MovementCommand.builder()
                        .actionType(MovementType.FORWARD)
                        .steps(1)
                        .build(),
                RotationCommand.builder()
                        .actionType(RotationType.RIGHT)
                        .build(),
                MovementCommand.builder()
                        .actionType(MovementType.FORWARD)
                        .steps(2)
                        .build()
        );
    }

}
