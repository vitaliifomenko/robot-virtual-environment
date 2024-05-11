package com.robotics.virtual.environment.service.script.commandparser;

import com.robotics.virtual.environment.configuration.DefaultApplicationProperties;
import com.robotics.virtual.environment.exception.parser.InvalidCommandException;
import com.robotics.virtual.environment.model.action.MovementType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.MovementCommand;
import com.robotics.virtual.environment.model.script.RawCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.robotics.virtual.environment.utils.ValidationUtils.validateAndConvert;

@AllArgsConstructor
@Service
public class MovementParser implements CommandParser<MovementType> {

    private final DefaultApplicationProperties defaultApplicationProperties;

    @Override
    public Command<MovementType> parse(RawCommand rawCommand) {
        final var stepsRange = defaultApplicationProperties.robot().script().steps().getRange();
        final var steps = Optional.ofNullable(rawCommand.xCoordinate())
                .flatMap(step -> validateAndConvert(step, stepsRange))
                .orElseThrow(() -> new InvalidCommandException(rawCommand));

        return MovementCommand.builder()
                .actionType(MovementType.valueOf(rawCommand.action()))
                .steps(steps)
                .build();
    }

    @Override
    public boolean canParse(RawCommand rawCommand) {
        return EnumUtils.isValidEnum(MovementType.class, rawCommand.action());
    }
}
