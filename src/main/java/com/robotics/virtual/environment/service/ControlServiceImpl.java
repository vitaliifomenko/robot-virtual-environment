package com.robotics.virtual.environment.service;

import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.state.RobotEnvironmentState;
import com.robotics.virtual.environment.service.command.CommandHandler;
import com.robotics.virtual.environment.service.command.MovementHandler;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class ControlServiceImpl implements ControlService {

    private final Map<Class<?>, CommandHandler<Command>> commandHandlers;

    public ControlServiceImpl(List<CommandHandler<Command>> commandHandlers) {
        this.commandHandlers = commandHandlers.stream()
                .collect(toMap(
                        handler -> ResolvableType.forClass(MovementHandler.class).resolveGeneric(),
                        Function.identity())
                );
    }

    @Override
    public Optional<RobotEnvironmentState> handleRobotControl(RobotEnvironmentState state, List<Command> commands) {
        return commands.stream()
                .map(command -> commandHandlers.get(command.getClass()).handle(state, command))
                .reduce((state1, state2) -> state2);
    }

}
