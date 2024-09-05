package ru.test_service.authorization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

@Schema
@Getter
@Setter
public class AuthorizationRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4882208474719165648L;

    @JsonIgnore
    private static final Integer UTC_ZERO_OFFSET = 0;

    @Schema(description = "Логин пользователя", example = "viktor123")
    private String login;

    @Schema(description = "Пароль пользователя", example = "1234")
    private String password;

    @Schema(description = "Смещение таймзоны пользователя в секундах", example = "14400")
    private Integer userOffsetSecond = UTC_ZERO_OFFSET;

    public Integer getUserOffsetSecond() {
        return userOffsetSecond == null ? UTC_ZERO_OFFSET : this.userOffsetSecond;
    }
}
