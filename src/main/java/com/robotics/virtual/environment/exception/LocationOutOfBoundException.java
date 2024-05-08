package com.robotics.virtual.environment.exception;

import com.robotics.virtual.environment.model.command.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocationOutOfBoundException extends RuntimeException {

//    private String messagePattern = "Unable to move to";

    private Command<?> command;

}
