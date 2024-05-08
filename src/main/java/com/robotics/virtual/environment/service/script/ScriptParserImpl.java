package com.robotics.virtual.environment.service.script;

import com.robotics.virtual.environment.exception.parser.InvalidCommandActionException;
import com.robotics.virtual.environment.exception.parser.InvalidCommandException;
import com.robotics.virtual.environment.exception.parser.NotFoundCommandException;
import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.script.RawCommand;
import com.robotics.virtual.environment.model.script.Script;
import com.robotics.virtual.environment.service.script.commandparser.CommandParser;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@AllArgsConstructor
@Service
public class ScriptParserImpl implements ScriptParser {

    private final String actionGroup = "ACTION";
    private final String xCoordinateGroup = "xCoordinate";
    private final String yCoordinateGroup = "yCoordinate";
    private final String directionGroup = "direction";

    private final Pattern commandPattern = Pattern.compile(
            String.format("^(?<%s>\\w+)\\s?(?<%s>\\d?)\\s?(?<%s>\\d?)\\s?(?<%s>\\w*?)$",
                    actionGroup, xCoordinateGroup, yCoordinateGroup, directionGroup)
    );

    private final List<CommandParser<? extends ActionType>> commandParsers;

    @Override
    public List<Command<? extends ActionType>> parseScript(Script script) {
        return emptyIfNull(script.rawScript()).stream()
                .filter(StringUtils::isNotBlank)
                .map(rawCommand -> rawCommand.trim().toUpperCase())
                .<Command<? extends ActionType>>map(this::parseCommand)
                .toList();
    }

    private Command<? extends ActionType> parseCommand(String rawCommand) {
        final var matcher = commandPattern.matcher(rawCommand);
        if (matcher.find()) {
            final var action = Optional.ofNullable(matcher.group(actionGroup))
                    .filter(StringUtils::isNotBlank)
                    .orElseThrow(() -> new InvalidCommandActionException(rawCommand));
            final var parsedCommand = RawCommand.builder()
                    .action(action)
                    .xCoordinate(matcher.group(xCoordinateGroup))
                    .yCoordinate(matcher.group(yCoordinateGroup))
                    .direction(matcher.group(directionGroup))
                    .build();

            return commandParsers.stream()
                    .filter(parser -> parser.canParse(parsedCommand))
                    .map(parser -> parser.parse(parsedCommand))
                    .findFirst()
                    .orElseThrow(() -> new InvalidCommandException(rawCommand));
        }
        throw new NotFoundCommandException(rawCommand);
    }

}
