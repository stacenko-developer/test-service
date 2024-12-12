package ru.test_service.lesson.controller;

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
import ru.test_service.lesson.bean.LessonServiceBean;
import ru.test_service.lesson.dto.CreateLessonDto;
import ru.test_service.lesson.dto.LessonDto;

import java.util.UUID;

@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
@Tag(name = "Уроки", description = "Управление уроками")
public class LessonController {

    private final LessonServiceBean lessonServiceBean;

    @GetMapping({"/{id}"})
    @Operation(
            summary = "Получить урок по идентификатору",
            description = "Получение урока по идентификатору"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LessonDto.class)
                    ),
                    description = "Возвращает урок по идентификатору"
            )
    })
    public ResponseEntity<?> getLessonById(
            @Parameter(description = "Идентификатор урока", required = true) @PathVariable UUID id
    ) {
        return new ResponseEntity<>(lessonServiceBean.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Создать урок",
            description = "Создание урока"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LessonDto.class)
                    ),
                    description = "Возвращает созданный урок"
            )
    })
    public ResponseEntity<?> createLesson(
            @Parameter(description = "Объект урока") @RequestBody CreateLessonDto user
    ) {
        return new ResponseEntity<>(lessonServiceBean.createLesson(user), HttpStatus.OK);
    }

    @PutMapping
    @Operation(
            summary = "Обновить данные урока",
            description = "Обновление данных урока"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LessonDto.class)
                    ),
                    description = "Возвращает обновленный урок"
            )
    })
    public ResponseEntity<?> updateLesson(
            @Parameter(description = "Объект урока") @RequestBody LessonDto user
    ) {
        return new ResponseEntity<>(lessonServiceBean.updateLesson(user), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    @Operation(
            summary = "Удалить урок",
            description = "Удаление урока"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Возвращает 200, если удаление прошло успешно"
            )
    })
    public ResponseEntity<?> deleteLesson(
            @Parameter(description = "Идентификатор урока", required = true) @PathVariable UUID id
    ) {
        lessonServiceBean.deleteLesson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}