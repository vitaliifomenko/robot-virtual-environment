package com.robotics.virtual.environment.service;

import com.robotics.virtual.environment.model.script.RawScript;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.model.state.environment.EnvironmentState;
import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.Location;
import com.robotics.virtual.environment.model.state.robot.RobotState;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.robotics.virtual.environment.TestDataFactory.getRobotEnvironmentState;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControlServiceTest {

    @Autowired
    private ControlService controlService;

    private static Stream<Arguments> scripts() {
        return Stream.of(
                Arguments.of(
                        "",
                        RobotEnvironmentState.builder()
                                .environmentState(EnvironmentState.builder()
                                        .width(4)
                                        .height(4)
                                        .build()
                                ).robotState(RobotState.defaultBuilder()
                                        .location(Location.builder()
                                                .xCoordinate(0)
                                                .yCoordinate(0)
                                                .build())
                                        .direction(Direction.EAST)
                                        .build()
                                ).build()
                ),
                Arguments.of(
                        """
                                POSITION 1 3 EAST
                                FORWARD 3
                                WAIT
                                TURNAROUND
                                FORWARD 1
                                RIGHT
                                FORWARD 2
                                """,
                        getRobotEnvironmentState()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("scripts")
    public void handleRobotControlTest(String script, RobotEnvironmentState expectedState) {
        assertThat(controlService.handleRobotControl(new RawScript(script)))
                .isEqualTo(expectedState);
    }

}
