package com.robotics.virtual.environment.exception.parser;

public class InvalidCommandActionException extends CommandParseException {

    public InvalidCommandActionException(String rawCommand) {
        super(rawCommand);
    }
}
