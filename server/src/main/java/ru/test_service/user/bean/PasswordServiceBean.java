package ru.test_service.user.bean;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class PasswordServiceBean {

    private final SecureRandom secureRandom;

    public String getRandomPassword() {
        final int leftLimit = 'a';
        final int rightLimit = 'z';
        final int targetStringLength = 10;

        return secureRandom.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
