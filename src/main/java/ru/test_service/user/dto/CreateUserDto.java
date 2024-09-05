package ru.test_service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

@Schema
@Getter
@Setter
public class CreateUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2901244697416952286L;

    @Schema(description = "Логин пользователя", example = "viktor123")
    private String login;

    @Schema(description = "Пароль пользователя", example = "1234")
    private String password;
}
