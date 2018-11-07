package com.example.WebService;

import org.springframework.stereotype.Component;

@javax.jws.WebService(serviceName = "HelloWorldService",portName = "HelloWordServiceImp",targetNamespace = "http://WebService.example.com/", endpointInterface = "com.example.WebService.HelloWordService")
@Component
public class HelloWordServiceImp implements HelloWordService {
    @Override
    public String getHello(String message) {
        return "Hello"+message;
    }
}
