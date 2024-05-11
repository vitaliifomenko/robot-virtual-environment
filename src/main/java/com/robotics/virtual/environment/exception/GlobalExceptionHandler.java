package com.robotics.virtual.environment.exception;

import com.robotics.virtual.environment.configuration.DefaultApplicationProperties;
import com.robotics.virtual.environment.exception.command.CommandLimitExceededException;
import com.robotics.virtual.environment.exception.command.LocationOutOfBoundException;
import com.robotics.virtual.environment.exception.command.MoveCommandOutOfBoundException;
import com.robotics.virtual.environment.exception.parser.CommandParseException;
import com.robotics.virtual.environment.model.script.RawScript;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final DefaultApplicationProperties defaultApplicationProperties;

    @ExceptionHandler(CommandLimitExceededException.class)
    public ModelAndView handleCommandLimitExceededException(CommandLimitExceededException ex) {
        return handleException(ex, "Commands limit exceeded, max: " +
                defaultApplicationProperties.robot().script().commands().getRange().getMaximum());
    }

    @ExceptionHandler(CommandParseException.class)
    public ModelAndView handleCommandParseException(CommandParseException ex) {
        return handleException(ex, "Invalid command: " + ex.getRawCommand());
    }

    @ExceptionHandler(LocationOutOfBoundException.class)
    public ModelAndView handleLocationOutOfBoundException(LocationOutOfBoundException ex) {
        return handleException(ex,
                String.format("Unable to execute command: %s. The location will be out of bounds", ex.getCommand())
        );
    }

    @ExceptionHandler(MoveCommandOutOfBoundException.class)
    public ModelAndView handleMoveCommandOutOfBoundException(MoveCommandOutOfBoundException ex) {
        return handleException(ex,
                String.format("Unable to execute command: %s. The location will be out of bounds", ex.getCommand())
        );
    }

    private ModelAndView handleException(Exception exception, String message) {
        log.error("[robot-virtual-environment] error: ", exception);
        final var model = new ModelAndView("script", "script", new RawScript());
        model.addObject("errorMessage", message);
        return model;
    }

}
