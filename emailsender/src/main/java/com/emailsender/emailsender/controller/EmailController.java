package com.emailsender.emailsender.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.emailsender.emailsender.helper.CustomResponse;
import com.emailsender.emailsender.service.EmailService;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // send email
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getMessage(), request.getSubject());
        return ResponseEntity.ok(CustomResponse.builder().messString("Email Sent Successfully...")
                .response(HttpStatus.OK).success(true).build());
    }

    @PostMapping("/send-with-html")
    public ResponseEntity<?> sendEmailwithHTML(@RequestBody EmailRequest request) {
        emailService.sendEmailWithHTML(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(CustomResponse.builder().messString("Email Sent Successfully").response(HttpStatus.OK)
                .success(true).build());

    }

    @PostMapping("/send-with-file")
    public ResponseEntity<?> sendEmailwithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file)
            throws IOException {
        emailService.sendEmailWithIS(request.getTo(), request.getSubject(), request.getMessage(),
                file.getInputStream(), file.getOriginalFilename());
        return ResponseEntity.ok(CustomResponse.builder().messString("Email Sent Successfully").response(HttpStatus.OK)
                .success(true).build());

    }
}
