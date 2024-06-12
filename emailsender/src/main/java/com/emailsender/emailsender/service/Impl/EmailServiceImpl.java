package com.emailsender.emailsender.service.Impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import jakarta.mail.util.ByteArrayDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailsender.emailsender.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        // TODO Auto-generated method stub
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("devshubham274@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent successfully from single to");

    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        // TODO Auto-generated method stub
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("devshubham274@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
        logger.info("Email send with Multiple to");

    }

    @Override
    public void sendEmailWithHTML(String to, String subject, String htmlContent) {
        // TODO Auto-generated method stub
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setTo(to);
            messageHelper.setFrom("devshubham274@gmail.com");
            messageHelper.setSubject(subject);
            messageHelper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            logger.info("Email sent with HTML content..");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void sendEmailWithFile(String to, String subject, String messgae, File file) {
        // TODO Auto-generated method stub
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("devshubham274@gmail.com");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(messgae);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            messageHelper.addAttachment(fileSystemResource.getFilename(), file);
            mailSender.send(mimeMessage);
            logger.info("Mail send successfully using file ...");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // Using file and then copied all the data using IS
    // @Override
    // public void sendEmailWithIS(String to, String subject, String message,
    // InputStream is) {
    // // TODO Auto-generated method stub
    // MimeMessage mimeMessage = mailSender.createMimeMessage();
    // try {
    // MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
    // messageHelper.setFrom("devshubham274@gmail.com");
    // messageHelper.setTo(to);
    // messageHelper.setSubject(subject);
    // messageHelper.setText(message);
    // File file = new File("src/main/resources/emails/test.JPG");
    // Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    // FileSystemResource fileSystemResource = new FileSystemResource(file);
    // messageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),
    // file);
    //
    // mailSender.send(mimeMessage);
    // logger.info("Mail send successfully using InputStream ...");
    // } catch (MessagingException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

    // Send attachement using datasource but send as only attachment no multipart
    // file
    @Override
    public void sendEmailWithIS(String to, String subject, String message, InputStream is, String filename) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("devshubham274@gmail.com");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);

            // Convert InputStream to ByteArrayDataSource
            ByteArrayDataSource dataSource = new ByteArrayDataSource(is, "application/octet-stream");

            // Add the attachment using MimeMessageHelper
            messageHelper.addAttachment(filename, dataSource);

            // Send the email
            mailSender.send(mimeMessage);
            logger.info("Mail sent successfully using InputStream ...");
        } catch (MessagingException | IOException e) {
            logger.error("Error sending email: " + e.getMessage(), e);
        } finally {
            // Close the input stream
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("Error closing input stream: " + e.getMessage(), e);
            }
        }
    }

}
