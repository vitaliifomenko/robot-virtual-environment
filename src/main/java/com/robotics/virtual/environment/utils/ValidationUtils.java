package com.robotics.virtual.environment.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.IntegerRange;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {

    public static Optional<Integer> toInteger(String rawNumber, IntegerRange range) {
        return Optional.ofNullable(rawNumber)
                .filter(NumberUtils::isDigits)
                .map(Integer::valueOf)
                .filter(range::contains);
    }

}
