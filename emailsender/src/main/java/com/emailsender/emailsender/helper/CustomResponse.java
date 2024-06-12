package com.emailsender.emailsender.helper;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.resource.HttpResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponse {

    private String messString;
    private HttpStatus response;
    private boolean success;
}
