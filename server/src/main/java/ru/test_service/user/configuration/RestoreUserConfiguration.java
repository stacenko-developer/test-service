package ru.test_service.user.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "restore-user")
@Getter
@Setter
public class RestoreUserConfiguration {
    private String restoreUserUsername;
    private String restoreUserSubject;
    private String restoreUserBody;
}
