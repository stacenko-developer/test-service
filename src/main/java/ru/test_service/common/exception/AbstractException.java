package ru.test_service.common.exception;

import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException {

    public String getMessage() {
        return null;
    }

    public String getDescription() {
        return null;
    }
}
