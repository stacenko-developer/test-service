package ru.test_service.lesson.exception;

import ru.test_service.common.exception.AbstractException;
import java.io.Serial;

public class LessonNameHasAlreadyExistException extends AbstractException {

    @Serial
    private static final long serialVersionUID = -562408553586961026L;

    @Override
    public String getMessage() {
        return "LESSON_NAME_HAS_ALREADY_EXIST";
    }

    @Override
    public String getDescription() {
        return "Урок с указанным названием уже зарегистрирован в системе";
    }
}
