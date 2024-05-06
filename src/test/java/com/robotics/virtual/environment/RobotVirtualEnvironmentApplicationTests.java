package com.robotics.virtual.environment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RobotVirtualEnvironmentApplicationTests {

    @Test
    void contextLoads() {
		final var value = 1;
        assertThat(value)
				.isEqualTo(1);
    }

}
