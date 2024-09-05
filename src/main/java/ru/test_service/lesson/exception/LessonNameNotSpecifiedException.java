package ru.test_service.lesson.exception;

import ru.test_service.common.exception.AbstractException;
import java.io.Serial;

public class LessonNameNotSpecifiedException extends AbstractException {

    @Serial
    private static final long serialVersionUID = 8402551187806625099L;

    @Override
    public String getMessage() {
        return "LESSON_NAME_NOT_SPECIFIED";
    }

    @Override
    public String getDescription() {
        return "Название урока не указано";
    }
}
