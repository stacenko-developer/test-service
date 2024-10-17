package ru.test_service.authorization.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "user")
public class UserTimeZoneProperties {
    private Integer timezoneOffsetSeconds;
}
