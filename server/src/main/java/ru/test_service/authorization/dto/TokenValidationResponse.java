package ru.test_service.authorization.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class TokenValidationResponse {

    private final UUID userId;
    private final boolean isActive;
    private final Integer userOffsetSecond;
}
