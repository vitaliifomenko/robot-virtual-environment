package com.robotics.virtual.environment.service.script.commandparser;

import com.robotics.virtual.environment.configuration.DefaultApplicationProperties;
import com.robotics.virtual.environment.exception.parser.InvalidCommandException;
import com.robotics.virtual.environment.model.action.LocationType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.LocationCommand;
import com.robotics.virtual.environment.model.script.RawCommand;
import com.robotics.virtual.environment.model.state.robot.Direction;
import com.robotics.virtual.environment.model.state.robot.Location;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.robotics.virtual.environment.utils.ValidationUtils.toInteger;

@AllArgsConstructor
@Service
public class LocationParser implements CommandParser<LocationType> {

    private final DefaultApplicationProperties defaultApplicationProperties;

    @Override
    public Command<LocationType> parse(RawCommand rawCommand) {

        final var locationConfiguration = defaultApplicationProperties.robot().location();

        final var xCoordinate = Optional.ofNullable(rawCommand.xCoordinate())
                .flatMap(coordinate -> toInteger(coordinate, locationConfiguration.xCoordinate().getRange()))
                .orElseThrow(() -> new InvalidCommandException(rawCommand));
        final var yCoordinate = Optional.ofNullable(rawCommand.yCoordinate())
                .flatMap(coordinate -> toInteger(coordinate, locationConfiguration.yCoordinate().getRange()))
                .orElseThrow(() -> new InvalidCommandException(rawCommand));

        return LocationCommand.builder()
                .actionType(LocationType.valueOf(rawCommand.action()))
                .direction(Direction.valueOf(rawCommand.direction()))
                .location(Location.builder()
                        .xCoordinate(xCoordinate)
                        .yCoordinate(yCoordinate)
                        .build())
                .build();
    }

    @Override
    public boolean canParse(RawCommand rawCommand) {
        return EnumUtils.isValidEnum(LocationType.class, rawCommand.action())
                && EnumUtils.isValidEnum(Direction.class, rawCommand.direction());
    }
}
