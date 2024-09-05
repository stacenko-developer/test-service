package ru.test_service.common.exception;

import java.io.Serial;

public class NotFoundException extends AbstractException {

    @Serial
    private static final long serialVersionUID = -3771281527287084687L;

    @Override
    public String getMessage() {
        return "NOT_FOUND";
    }

    @Override
    public String getDescription() {
        return "Сущность не найдена";
    }

}
