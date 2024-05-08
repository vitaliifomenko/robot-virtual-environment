package com.robotics.virtual.environment;

import com.robotics.virtual.environment.configuration.DefaultApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({DefaultApplicationProperties.class})
public class RobotVirtualEnvironmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotVirtualEnvironmentApplication.class, args);
    }

}
