package ru.test_service.user.exception;

import ru.test_service.common.exception.AbstractException;

import java.io.Serial;

public class UserPasswordNotSpecifiedException extends AbstractException {

    @Serial
    private static final long serialVersionUID = 8279838379840173510L;

    @Override
    public String getMessage() {
        return "USER_PASSWORD_NOT_SPECIFIED";
    }

    @Override
    public String getDescription() {
        return "Пароль пользователя не указан";
    }
}
