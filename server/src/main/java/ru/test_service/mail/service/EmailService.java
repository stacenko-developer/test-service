package ru.test_service.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.test_service.mail.dto.EmailDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(EmailDto emailDto) {
        try {
            javaMailSender.send(mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setTo(emailDto.getRecipient());
                messageHelper.setFrom(emailDto.getEmailSender());
                messageHelper.setSubject(emailDto.getSubject());
                messageHelper.setText(emailDto.getBody(), true);
            });
            log.info("Message sent to {}", emailDto.getEmailSender());

        } catch (Exception e) {
            log.error("send email - Dto: \n{}\nhave troubles... \n{}", emailDto, e.getMessage());
        }

    }
}