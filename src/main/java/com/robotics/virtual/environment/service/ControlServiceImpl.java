package com.robotics.virtual.environment.service;

import com.robotics.virtual.environment.configuration.DefaultApplicationProperties;
import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.LocationCommand;
import com.robotics.virtual.environment.model.environment.Environment;
import com.robotics.virtual.environment.model.script.Script;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.model.state.environment.EnvironmentState;
import com.robotics.virtual.environment.model.state.robot.Location;
import com.robotics.virtual.environment.model.state.robot.RobotState;
import com.robotics.virtual.environment.service.command.CommandHandler;
import com.robotics.virtual.environment.service.script.ScriptParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class ControlServiceImpl implements ControlService {

    private final Map<Class<? extends Command<?>>, CommandHandler<? extends Command<? extends ActionType>>> commandHandlers;
    private final ScriptParser scriptParser;
    private final DefaultApplicationProperties defaultApplicationProperties;

    public ControlServiceImpl(List<CommandHandler<? extends Command<? extends ActionType>>> commandHandlers,
                              ScriptParser scriptParser, DefaultApplicationProperties defaultApplicationProperties) {
        this.commandHandlers = convertCommandHandlers(commandHandlers);
        this.scriptParser = scriptParser;
        this.defaultApplicationProperties = defaultApplicationProperties;
    }

    @Override
    public RobotEnvironmentState handleRobotControl(Script script, Environment environment) {
        final var commands = scriptParser.parseScript(script);
        final var state = RobotEnvironmentState.builder()
                .environmentState(getEnvironmentState(environment))
                .robotState(getRobotState(commands))
                .build();
        return handleRobotControl(state, commands);
    }

    public RobotEnvironmentState handleRobotControl(
            RobotEnvironmentState initialState,
            List<? extends Command<? extends ActionType>> commands
    ) {
        return commands.stream()
                .reduce(
                        initialState,
                        (state, command) -> commandHandlers.get(command.getClass()).handle(state, command),
                        (prevState, nextState) -> nextState
                );
    }

    private EnvironmentState getEnvironmentState(Environment environment) {
        return EnvironmentState.builder()
                .width(environment.width().orElse(defaultApplicationProperties.environment().width().getDefaultValue()))
                .height(environment.height().orElse(defaultApplicationProperties.environment().height().getDefaultValue()))
                .build();
    }

    private RobotState getRobotState(List<? extends Command<? extends ActionType>> commands) {
        final var firstCommand = commands.stream()
                .findFirst()
                .filter(command -> command instanceof LocationCommand)
                .map(command -> (LocationCommand) command);

        return RobotState.defaultBuilder()
                .location(firstCommand.map(LocationCommand::location)
                        .orElse(Location.builder()
                                .xCoordinate(defaultApplicationProperties.robot().location().xCoordinate().getDefaultValue())
                                .yCoordinate(defaultApplicationProperties.robot().location().yCoordinate().getDefaultValue())
                                .build()))
                .direction(firstCommand.map(LocationCommand::direction)
                        .orElse(defaultApplicationProperties.robot().direction()))
                .build();
    }

    private Map<Class<? extends Command<?>>, CommandHandler<? extends Command<? extends ActionType>>> convertCommandHandlers(
            List<CommandHandler<? extends Command<? extends ActionType>>> commandHandlers
    ) {
        return commandHandlers.stream()
                .collect(toMap(CommandHandler::getHandleCommand, Function.identity()));
    }

}
