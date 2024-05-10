package com.robotics.virtual.environment.exception.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class CommandParseException extends RuntimeException {

    private String rawCommand;

}
