package com.robotics.virtual.environment.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.IntegerRange;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {

    public static Optional<Integer> validateAndConvert(String rawNumber, IntegerRange range) {
        return Optional.ofNullable(rawNumber)
                .filter(NumberUtils::isDigits)
                .map(Integer::valueOf)
                .filter(range::contains);
    }

}
