package com.robotics.virtual.environment.controller;

import com.robotics.virtual.environment.model.environment.Environment;
import com.robotics.virtual.environment.model.script.RawScript;
import com.robotics.virtual.environment.service.ControlService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping
public class RobotController {

    private final ControlService controlService;

    @GetMapping
    public ModelAndView showScriptPage() {
        return new ModelAndView("script", "script", new RawScript());
    }

    @PostMapping(value = "/process", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView action(RawScript script, BindingResult result,
                               @RequestParam(required = false) Integer envWidth,
                               @RequestParam(required = false) Integer envHeight) {
        return new ModelAndView("virtual-environment", "state",
                controlService.handleRobotControl(script, new Environment(envWidth, envHeight))
        );
    }

}
