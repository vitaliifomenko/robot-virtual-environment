package com.robotics.virtual.environment.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${openapi.robot.base-path:/}")
public class RobotController {

    @PostMapping(value = "/action", produces = MediaType.APPLICATION_JSON_VALUE)
    public String action(){
        return "test";
    }

}
