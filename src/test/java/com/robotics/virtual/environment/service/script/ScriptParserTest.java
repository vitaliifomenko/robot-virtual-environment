package com.robotics.virtual.environment.service.script;

import com.robotics.virtual.environment.exception.parser.CommandParseException;
import com.robotics.virtual.environment.exception.parser.InvalidCommandException;
import com.robotics.virtual.environment.exception.parser.NotFoundCommandException;
import com.robotics.virtual.environment.model.action.ActionType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.script.RawScript;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static com.robotics.virtual.environment.TestDataFactory.getCommands;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ScriptParserTest {

    @Autowired
    private ScriptParser scriptParser;

    private static Stream<Arguments> scripts() {
        return Stream.of(
                Arguments.of(
                        "",
                        List.of()
                ),
                Arguments.of(
                        """
                                POSITION 1 3 EAST
                                FORWARD 3
                                WAIT
                                TURNAROUND
                                FORWARD 1
                                RIGHT
                                FORWARD 2
                                """,
                        getCommands()
                )
        );
    }

    private static Stream<Arguments> invalidScripts() {
        return Stream.of(
                Arguments.of(
                        "POS%0ad",
                        NotFoundCommandException.class
                ),
                Arguments.of(
                        "FORW 1",
                        InvalidCommandException.class
                ),
                Arguments.of(
                        "1 2 3 4",
                        InvalidCommandException.class
                ),
                Arguments.of(
                        "FORWARD",
                        InvalidCommandException.class
                )
        );
    }

    @ParameterizedTest
    @MethodSource("scripts")
    public void parseScriptTest(String script, List<Command<? extends ActionType>> expectedCommands) {
        Assertions.assertThat(scriptParser.parseScript(new RawScript(script)))
                .isEqualTo(expectedCommands);
    }


    @ParameterizedTest
    @MethodSource("invalidScripts")
    public void parseScriptThrowErrorTest(String script, Class<? extends CommandParseException> expectedError) {
        assertThrows(expectedError,
                () -> scriptParser.parseScript(new RawScript(script)));
    }

}
