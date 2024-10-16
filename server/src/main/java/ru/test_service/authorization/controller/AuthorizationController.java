package ru.test_service.authorization.controller;

import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.test_service.authorization.configuration.jwt.JwtConfigurationProperties;
import ru.test_service.authorization.dto.AuthorizationRequestDto;
import ru.test_service.authorization.dto.AuthorizationResponseDto;
import ru.test_service.user.bean.UserServiceBean;
import ru.test_service.user.dto.SecuredUserDto;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.util.Date;

@ConditionalOnProperty(value = "authentication.jwt.enabled", matchIfMissing = true)
@Tag(name = "Авторизация", description = "Управление авторизацией")
@RequiredArgsConstructor
@RestController
public class AuthorizationController {

    private final UserServiceBean userServiceBean;
    private final JwtConfigurationProperties jwtConfigurationProperties;

    @CrossOrigin
    @PostMapping("/login")
    @Operation(
            summary = "Авторизоваться",
            description = "Авторизация по логину и паролю"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthorizationResponseDto.class)
                    ),
                    description = "Возвращает JWT токен, если авторизация прошла успешно"
            )
    })
    public ResponseEntity<?> login(@RequestBody AuthorizationRequestDto authorizationRequestDto) {
        SecuredUserDto securedUserDto = userServiceBean.findSecuredByLogin(authorizationRequestDto.getLogin());

        if (securedUserDto == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        if (!encoder.matches(authorizationRequestDto.getPassword(), securedUserDto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Instant now = Instant.now();
        String token =
                Jwts.builder()
                        .header()
                        .add("type", "access")
                        .and()
                        .subject(securedUserDto.getId().toString())
                        .claim("userOffsetSecond", authorizationRequestDto.getUserOffsetSecond())
                        .issuedAt(Date.from(now))
                        .expiration(Date.from(now.plusSeconds(jwtConfigurationProperties.getExpiration())))
                        .signWith(Keys.hmacShaKeyFor(jwtConfigurationProperties.getSecret().getBytes()))
                        .compact();

        AuthorizationResponseDto authorizationResponseDto = new AuthorizationResponseDto();
        authorizationResponseDto.setToken(token);

        return new ResponseEntity<>(authorizationResponseDto, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/logout")
    @Operation(
            summary = "Выйти из учетной записи",
            description = "Выход из учетной записи"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Возвращает 200 код, если выход выполнен успешно"
            )
    })
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
