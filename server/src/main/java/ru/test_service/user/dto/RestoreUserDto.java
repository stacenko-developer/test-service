package ru.test_service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

@Schema
@Getter
@Setter
public class RestoreUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -5074181596770720923L;

    @Schema(description = "Почта пользователя", example = "viktor123@mail.ru")
    private String email;
}
