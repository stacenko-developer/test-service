package ru.test_service.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test_service.user.bean.UserServiceBean;
import ru.test_service.user.dto.SecuredUserDto;
import ru.test_service.user.dto.UserDto;
import ru.test_service.user.dto.CreateUserDto;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "Пользователи", description = "Управление пользователями")
public class UserController {

    private final UserServiceBean userServiceBean;

    @GetMapping({"/{id}"})
    @Operation(
            summary = "Получить пользователя по идентификатору",
            description = "Получение пользователя по идентификатору"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    ),
                    description = "Возвращает пользователя по идентификатору"
            )
    })
    public ResponseEntity<?> getUserById(
            @Parameter(description = "Идентификатор пользователя", required = true) @PathVariable UUID id
    ) {
        return new ResponseEntity<>(userServiceBean.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Создать пользователя",
            description = "Создание пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    ),
                    description = "Возвращает созданного пользователя"
            )
    })
    public ResponseEntity<?> createUser(
            @Parameter(description = "Объект пользователя") @RequestBody CreateUserDto user
    ) {
        return new ResponseEntity<>(userServiceBean.createUser(user), HttpStatus.OK);
    }

    @PutMapping
    @Operation(
            summary = "Обновить данные пользователя",
            description = "Обновление данных пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    ),
                    description = "Возвращает обновленного пользователя"
            )
    })
    public ResponseEntity<?> updateUser(
            @Parameter(description = "Объект пользователя") @RequestBody SecuredUserDto user
    ) {
        return new ResponseEntity<>(userServiceBean.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    @Operation(
            summary = "Удалить пользователя",
            description = "Удаление пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Возвращает 200, если удаление прошло успешно"
            )
    })
    public ResponseEntity<?> deleteUser(
            @Parameter(description = "Идентификатор пользователя", required = true) @PathVariable UUID id
    ) {
        userServiceBean.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
