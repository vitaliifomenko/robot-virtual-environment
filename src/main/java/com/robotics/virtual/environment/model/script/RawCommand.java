package com.robotics.virtual.environment.model.script;

import lombok.Builder;

@Builder
public record RawCommand(String action, String xCoordinate, String yCoordinate, String direction) {

}
