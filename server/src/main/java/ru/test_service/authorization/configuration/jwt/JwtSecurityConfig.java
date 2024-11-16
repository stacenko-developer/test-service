package ru.test_service.authorization.configuration.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.test_service.authorization.configuration.UserTimeZoneProperties;
import ru.test_service.authorization.configuration.filter.JwtAuthFilter;
import ru.test_service.authorization.service.TokenValidationService;
import ru.test_service.user.bean.UserServiceBean;


@ConditionalOnProperty(value = "authentication.jwt.enabled", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig {

    private final UserServiceBean userServiceBean;
    private final UserTimeZoneProperties userTimeZoneProperties;

    @Autowired(required = false)
    private TokenValidationService tokenValidationService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/v3/**",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/login",
                                "/user/**",
                                "/error",
                                "/404"
                        ).permitAll()
                        .anyRequest().hasAuthority("ADMINISTRATOR")
                )
                .anonymous(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthFilter(userServiceBean, tokenValidationService, userTimeZoneProperties), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }

}
