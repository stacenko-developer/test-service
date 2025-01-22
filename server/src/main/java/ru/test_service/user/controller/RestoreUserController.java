package ru.test_service.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test_service.user.bean.UserServiceBean;
import ru.test_service.user.dto.RestoreUserDto;

@RestController
@RequestMapping("/restore-user")
@AllArgsConstructor
@Tag(name = "Восстановление учетных записей", description = "Управление восстановлением учетных записей")
public class RestoreUserController {

    private final UserServiceBean userServiceBean;

    @PostMapping("/restore")
    @Operation(
            summary = "Восстановить учетную запись",
            description = "Восстанавливает учетную запись"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает 200, если все прошло успешно"
    )
    public ResponseEntity<?> restoreUser(
            @Parameter(description = "Данные для восстановления пользователя")
            @RequestBody RestoreUserDto restoreUserDto) {
        userServiceBean.restoreUser(restoreUserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
