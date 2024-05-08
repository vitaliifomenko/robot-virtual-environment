package com.robotics.virtual.environment.exception.parser;

import com.robotics.virtual.environment.model.script.RawCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class InvalidCommandException extends CommandParseException {

    public InvalidCommandException(String rawCommand) {
        super(rawCommand);
    }

    public InvalidCommandException(RawCommand rawCommand) {
        super(ToStringBuilder.reflectionToString(rawCommand));
    }
}
