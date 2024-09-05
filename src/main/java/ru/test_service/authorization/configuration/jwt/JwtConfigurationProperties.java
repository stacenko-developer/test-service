package ru.test_service.authorization.configuration.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConditionalOnProperty(value = "authentication.jwt.enabled", matchIfMissing = true)
@ConfigurationProperties(prefix = "authentication.jwt")
public class JwtConfigurationProperties {

    private Boolean enabled;
    private int expiration;
    private String secret;
}
