package com.robotics.virtual.environment.exception.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CommandParseException extends RuntimeException {

    private String rawCommand;

}
