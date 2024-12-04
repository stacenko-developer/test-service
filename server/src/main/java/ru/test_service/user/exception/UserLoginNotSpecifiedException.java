package ru.test_service.user.exception;

import ru.test_service.common.exception.AbstractException;

import java.io.Serial;

public class UserLoginNotSpecifiedException extends AbstractException {

    @Serial
    private static final long serialVersionUID = 8402551187806625099L;

    @Override
    public String getMessage() {
        return "USER_LOGIN_NOT_SPECIFIED";
    }

    @Override
    public String getDescription() {
        return "Логин пользователя не указан";
    }
}
