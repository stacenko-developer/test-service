package ru.test_service.mail.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EmailDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -895051368693993512L;

    private String subject;
    private String body;
    private String emailSender;
    private String recipient;
}
