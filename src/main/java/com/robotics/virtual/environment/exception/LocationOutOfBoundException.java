package com.robotics.virtual.environment.exception;

import com.robotics.virtual.environment.model.command.MovementCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocationOutOfBoundException extends RuntimeException {

//    private String messagePattern = "Unable to move to";

    private MovementCommand movementCommand;

}
