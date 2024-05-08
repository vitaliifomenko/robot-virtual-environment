package com.robotics.virtual.environment.service.script.commandparser;

import com.robotics.virtual.environment.model.action.WaitType;
import com.robotics.virtual.environment.model.command.Command;
import com.robotics.virtual.environment.model.command.WaitCommand;
import com.robotics.virtual.environment.model.script.RawCommand;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

@Service
public class WaitParser implements CommandParser<WaitType> {

    @Override
    public Command<WaitType> parse(RawCommand rawCommand) {
        return WaitCommand.builder()
                .actionType(WaitType.valueOf(rawCommand.action()))
                .build();
    }

    @Override
    public boolean canParse(RawCommand rawCommand) {
        return EnumUtils.isValidEnum(WaitType.class, rawCommand.action());
    }
}
