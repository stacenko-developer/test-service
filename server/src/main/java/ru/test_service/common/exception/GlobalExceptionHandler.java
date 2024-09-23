package ru.test_service.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<?> handleException(AbstractException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseErrorDto(ex.getMessage(), ex.getDescription()));
    }
}
