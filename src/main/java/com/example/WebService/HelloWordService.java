package com.example.WebService;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
@SuppressWarnings("restriction")
@WebService(name = "HelloWorldService",targetNamespace = "http://WebService.example.com/")
@Service
public interface HelloWordService {
    @WebMethod
    String getHello(@WebParam(name = "message") String message);
}
