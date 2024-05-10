package com.robotics.virtual.environment.model.script;

import org.apache.commons.lang3.StringUtils;

public record RawScript(String rawScript) {

    public RawScript() {
        this(StringUtils.EMPTY);
    }

}
