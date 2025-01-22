package ru.test_service.lesson.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Schema
@Getter
@Setter
public class LessonDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4293625543875948191L;

    @Schema(description = "Логин пользователя", example = "216c1bd3-a698-4b98-a91c-256090a695ca")
    private UUID id;

    @Schema(description = "Название урока", example = "Урок 1")
    private String name;
}
