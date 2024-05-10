package com.robotics.virtual.environment.service;

import com.robotics.virtual.environment.model.script.RawScript;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;

public interface ControlService {

    RobotEnvironmentState handleRobotControl(RawScript script);

}
