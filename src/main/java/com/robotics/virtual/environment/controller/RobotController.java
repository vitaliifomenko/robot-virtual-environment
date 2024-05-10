package com.robotics.virtual.environment.controller;

import com.robotics.virtual.environment.model.script.RawScript;
import com.robotics.virtual.environment.service.ControlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping
public class RobotController {

    private final ControlService controlService;

    @GetMapping
    public ModelAndView showScript() {
        return new ModelAndView("script", "script", new RawScript());
    }

    @PostMapping(value = "/process")
    public ModelAndView action(RawScript script) {
        return new ModelAndView("virtual-environment", "state",
                controlService.handleRobotControl(script)
        );
    }

}
