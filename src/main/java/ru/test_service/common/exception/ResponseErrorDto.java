package ru.test_service.common.exception;

import lombok.Getter;
import lombok.Setter;
import ru.test_service.common.ResponseDto;
import java.io.Serial;

@Getter
@Setter
public class ResponseErrorDto extends ResponseDto {

    @Serial
    private static final long serialVersionUID = 5176965618930263954L;

    private String description;

    public ResponseErrorDto(String errCode, String description) {
        super(errCode);
        this.description = description;
    }
}
