package com.robotics.virtual.environment.controller;

import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.model.state.environment.EnvironmentState;
import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.Location;
import com.robotics.virtual.environment.model.state.robot.RobotState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.stream.Stream;

import static com.robotics.virtual.environment.TestDataFactory.getRobotEnvironmentState;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@EnableWebMvc
@AutoConfigureMockMvc
public class RobotControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    public void scriptPageLoadingTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("script"));
    }

    @ParameterizedTest
    @MethodSource("scripts")
    public void scriptPageProcessTest(String script, RobotEnvironmentState expectedState) throws Exception {
        mockMvc.perform(post("/process")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("rawScript", script)
                )
                .andExpect(status().isOk())
                .andExpect(model().attribute("state", expectedState))
                .andReturn();
    }

}
