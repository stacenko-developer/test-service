package ru.test_service.user.exception;

import ru.test_service.common.exception.AbstractException;

import java.io.Serial;

public class UserLoginHasAlreadyExistException extends AbstractException {

    @Serial
    private static final long serialVersionUID = -562408553586961026L;

    @Override
    public String getMessage() {
        return "USER_LOGIN_HAS_ALREADY_EXIST";
    }

    @Override
    public String getDescription() {
        return "Пользователь с указанным логином уже зарегистрирован в системе";
    }
}
