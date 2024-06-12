package com.emailsender.emailsender.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public interface EmailService {

    // Send email to single person
    void sendEmail(String to, String subject, String message);

    // Send email to multiple person
    void sendEmail(String[] to, String subject, String message);

    // Send email with HTML
    void sendEmailWithHTML(String to, String subject, String message);

    // Send email with file
    void sendEmailWithFile(String to, String subject, String messgae, File file);

    void sendEmailWithIS(String to, String subject, String messgae, InputStream file, String filename);

}
