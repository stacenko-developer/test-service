package ru.test_service.user.exception;

import ru.test_service.common.exception.AbstractException;
import java.io.Serial;

public class EmailNotSpecifiedException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 5212128436443683750L;

    @Override
    public String getMessage() {
        return "EMAIL_NOT_SPECIFIED";
    }

    @Override
    public String getDescription() {
        return "Электронный адрес не указан";
    }
}
