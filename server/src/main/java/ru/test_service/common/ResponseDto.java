package ru.test_service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -354464868150594206L;

    private String errCode;
    private Object content;

    public ResponseDto(String errCode) {
        this.errCode = errCode;
    }
}
