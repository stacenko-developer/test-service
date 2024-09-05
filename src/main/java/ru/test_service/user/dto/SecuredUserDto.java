package ru.test_service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class SecuredUserDto extends UserDto {

    @Schema(description = "Пароль пользователя", example = "1234")
    private String password;
}
