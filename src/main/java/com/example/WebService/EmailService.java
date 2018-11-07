package com.example.WebService;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@SuppressWarnings("restriction")
@WebService(name = "EmailService",targetNamespace = "http://WebService.example.com/")
@Service
public interface EmailService {
    @WebMethod
    public String sendEmail(String url,String body);

    @WebMethod
    public String sendEmailBatch(String[] urls,String body);

    @WebMethod
    public String validEmailAddress(String url);

}
