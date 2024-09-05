package ru.test_service.lesson.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

@Schema
@Getter
@Setter
public class CreateLessonDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3859554750043889000L;

    @Schema(description = "Название урока", example = "Урок 1")
    private String name;
}
