package ru.test_service.authorization.configuration.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.test_service.authorization.configuration.UserTimeZoneProperties;
import ru.test_service.authorization.dto.SpringSecurityPrincipal;
import ru.test_service.authorization.dto.TokenValidationResponse;
import ru.test_service.authorization.exception.AuthenticationFailException;
import ru.test_service.authorization.service.TokenValidationService;
import ru.test_service.common.exception.NotFoundException;
import ru.test_service.user.bean.UserServiceBean;
import ru.test_service.user.dto.UserDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public final class JwtAuthFilter extends OncePerRequestFilter {

    private final static String HEADER_PREFIX = "Bearer";
    private final static String AUTHORIZATION_HEADER = "Authorization";

    private final UserServiceBean userServiceBean;
    private final TokenValidationService tokenValidationService;
    private final UserTimeZoneProperties userTimeZoneProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (
                SecurityContextHolder.getContext().getAuthentication() == null ||
                        !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        ) {

            if (StringUtils.isNotBlank(token) && token.startsWith(HEADER_PREFIX + " ")) {
                token = token.replace(HEADER_PREFIX + " ", "");

                if (StringUtils.isBlank(token) || token.equals("null")) {
                    SecurityContextHolder.clearContext();
                } else {
                    try {
                        jwtTokenAuthentication(token);
                    } catch (AuthenticationFailException authenticationFailException) {
                        log.debug("Authentication failed.");
                    } catch (ExpiredJwtException expiredJwtException) {
                        log.debug("Authentication failed because the access token is expired");
                    } catch (MalformedJwtException malformedJwtException) {
                        log.debug("Authentication failed because the access token was corrupted");
                    } catch (NotFoundException gpnValueNotFoundException) {
                        log.debug("Authentication failed because the user is not found");
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void jwtTokenAuthentication(String token) {

        TokenValidationResponse tokenValidationResponse = tokenValidationService.validate(token);

        if (!tokenValidationResponse.isActive()) {
            throw new AuthenticationFailException();
        }

        UserDto user = userServiceBean.findById(tokenValidationResponse.getUserId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        });

        SpringSecurityPrincipal.SpringSecurityPrincipalBuilder principal = SpringSecurityPrincipal
                .builder()
                .id(user.getId())
                .username(user.getLogin())
                .authorities(authorities)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .userOffsetSecond(getUserTimeOffsetSecond(tokenValidationResponse.getUserOffsetSecond()));

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                principal.build(),
                null,
                authorities);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Integer getUserTimeOffsetSecond(Integer valueFromToken) {
        if (valueFromToken == null || valueFromToken.equals(0)) {
            return userTimeZoneProperties.getTimezoneOffsetSeconds();
        }

        return valueFromToken;
    }

}