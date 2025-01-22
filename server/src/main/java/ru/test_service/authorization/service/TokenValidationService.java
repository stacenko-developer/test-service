package ru.test_service.authorization.service;

import ru.test_service.authorization.dto.TokenValidationResponse;

public interface TokenValidationService {
    TokenValidationResponse validate(String token);
}
