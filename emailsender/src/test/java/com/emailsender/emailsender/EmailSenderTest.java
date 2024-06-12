package com.emailsender.emailsender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emailsender.emailsender.service.EmailService;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmail() {
        emailService.sendEmail("sk37428@gmail.com", "Testing from Springboot Application", "Hey! How are you ?");
        System.out.println("Sending Email..");
    }

    @Test
    void sendEmailtoMultiple() {
        String[] recipient = { "sk37428@gmail.com", "das.athina96@gmail.com" };
        emailService.sendEmail(recipient, "Testing from Springboot Application", "Hey! How are you ?");
        System.out.println("Sending Email..");
    }

    @Test
    void sendEmailWithHTML() {

        String html = "<h1 style='color:red;'>Shubham</h1>"
                + "<h2>Kumar</h2>";
        emailService.sendEmailWithHTML("sk37428@gmail.com", "Testing from Springboot Application", html);
        System.out.println("Email sent using html...");
    }

    @Test
    void sendEmailWithFile() {
        emailService.sendEmailWithFile("das.athina96@gmail.com",
                "Email contain file",
                "Please be careful before opening file it will fire you from your job",
                new File("E:\\projects\\emailsenderapp\\emailsenderapp\\emailsender\\src\\main\\resources\\n" + //
                        "ati_radhika.JPG"));
    }

    // void sendEmailWithIS() {

    // File file = new File(
    // "E:\\projects\\emailsenderapp\\emailsenderapp\\emailsender\\src\\main\\resources\\static\\n"
    // + //
    // "ati_radhika.JPG");
    // try {
    // InputStream is = new FileInputStream(file);
    // emailService.sendEmailWithIS("sk37428@gmail.com",
    // "Test with IS",
    // "Testing with IS be careful", is);
    // } catch (FileNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // }
}
