package com.robotics.virtual.environment.service.script.commandparser;

import com.robotics.virtual.environment.model.action.RotationType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.RotationCommand;
import com.robotics.virtual.environment.model.script.RawCommand;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

@Service
public class RotationParser implements CommandParser<RotationType> {

    @Override
    public Command<RotationType> parse(RawCommand rawCommand) {
        return RotationCommand.builder()
                .actionType(RotationType.valueOf(rawCommand.action()))
                .build();
    }

    @Override
    public boolean canParse(RawCommand rawCommand) {
        return EnumUtils.isValidEnum(RotationType.class, rawCommand.action());
    }
}
