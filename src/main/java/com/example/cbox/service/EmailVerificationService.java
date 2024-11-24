package com.example.cbox.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jmx.export.metadata.InvalidMetadataException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationService {
    public static final String MESSAGE = "Your verification code is: %d";
    public static final String BASIC_GREETING_IMG_PATH = "Your verification code is: %d";
    public static final String FROM = "cbox@noreply.com";
    public static final String SUBJECT = "Verification code for CBox";
    public final JavaMailSender emailSender;

    public void sendSimpleEmail(String toAddress, int sec) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(MESSAGE.formatted(sec), true);
            helper.setTo(toAddress);
            helper.setSubject(SUBJECT);
            helper.setFrom(FROM);
            emailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            log.warn("Failed to send email", e);
            throw new InvalidMetadataException("Failed to send email");
        }
    }

    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
            messageHelper.addAttachment("Purchase Order", file);
            emailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            log.warn("Failed to send email", e);
            throw new InvalidMetadataException("Failed to send email");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}