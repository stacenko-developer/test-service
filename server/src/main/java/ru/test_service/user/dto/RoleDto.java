package ru.test_service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Schema
@Getter
@Setter
public class RoleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 570134845986694905L;

    @Schema(description = "Идентификатор роли", example = "216c1bd3-a698-4b98-a91c-256090a695ca")
    private UUID id;

    @Schema(description = "Название роли", example = "Администратор")
    private String name;

    @Schema(description = "Код роли", example = "ADMINISTRATOR")
    private String code;
}
