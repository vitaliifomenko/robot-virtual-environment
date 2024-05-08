package com.robotics.virtual.environment.exception.parser;

public class NotFoundCommandException extends CommandParseException {

    public NotFoundCommandException(String rawCommand) {
        super(rawCommand);
    }
}
