package ru.test_service.authorization.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.test_service.authorization.configuration.jwt.JwtConfigurationProperties;
import ru.test_service.authorization.dto.TokenValidationResponse;

import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

@ConditionalOnProperty(value = "authentication.jwt.enabled", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class TokenValidator implements TokenValidationService {

    private final JwtConfigurationProperties jwtConfigurationProperties;

    @Override
    public TokenValidationResponse validate(String token) {
        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(new SecretKeySpec(jwtConfigurationProperties.getSecret().getBytes(), "HmacSHA512"))
                .build()
                .parseSignedClaims(token);

        Integer userOffsetSecond = 0;

        if (claims.getPayload().containsKey("userOffsetSecond")) {
            userOffsetSecond = Integer.valueOf(String.valueOf(claims.getPayload().get("userOffsetSecond")));
        }

        return new TokenValidationResponse(
                UUID.fromString(String.valueOf(claims.getPayload().get("sub"))),
                true,
                userOffsetSecond
        );
    }

}
