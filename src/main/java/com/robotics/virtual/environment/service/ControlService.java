package com.robotics.virtual.environment.service;

import com.robotics.virtual.environment.model.environment.Environment;
import com.robotics.virtual.environment.model.script.Script;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;

public interface ControlService {

    RobotEnvironmentState handleRobotControl(Script script, Environment environment);

}
