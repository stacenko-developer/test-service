package ru.test_service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.test_service.lesson.dto.LessonDto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Schema
@Getter
@Setter
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3041660627641366822L;

    @Schema(description = "Идентификатор пользователя",
            example = "216c1bd3-a698-4b98-a91c-256090a695ca")
    private UUID id;

    @Schema(description = "Логин пользователя", example = "viktor123")
    private String login;

    @Schema(description = "Пароль пользователя", example = "1234")
    private String password;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "Отчество пользователя", example = "Иванович")
    private String patronymic;

    @Schema(description = "Почта пользователя", example = "viktor123@mail.ru")
    private String email;

    @Schema(description = "Роли пользователя")
    private List<RoleDto> roles = new ArrayList<>();

    @Schema(description = "Уроки для пользователя")
    private List<LessonDto> lessons = new ArrayList<>();
}
