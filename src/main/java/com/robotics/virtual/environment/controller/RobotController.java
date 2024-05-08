package com.robotics.virtual.environment.controller;

import com.robotics.virtual.environment.model.environment.Environment;
import com.robotics.virtual.environment.model.script.Script;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.service.ControlService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${openapi.robot.base-path:/}")
public class RobotController {

    private final ControlService controlService;

    @PostMapping(value = "/action", produces = MediaType.APPLICATION_JSON_VALUE)
    public RobotEnvironmentState action(@RequestBody List<String> rawScript,
                                                  @RequestParam(required = false) Integer envWidth,
                                                  @RequestParam(required = false) Integer envHeight) {
        return controlService.handleRobotControl(new Script(rawScript), new Environment(envWidth, envHeight));
    }

}
