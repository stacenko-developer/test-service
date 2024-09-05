package ru.test_service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Schema
@Getter
@Setter
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3041660627641366822L;

    @Schema(description = "Логин пользователя", example = "216c1bd3-a698-4b98-a91c-256090a695ca")
    private UUID id;

    @Schema(description = "Логин пользователя", example = "viktor123")
    private String login;

    @Schema(description = "Роли пользователя")
    private List<RoleDto> roles;
}
