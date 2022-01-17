package com.partha.ios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting:default value}")
    private String greetingMessage;

    @Value("some static message")
    private String staticMessage;

    @Value("${my.list.values}")
    private String listValues;

    @Autowired
    private DBSettings dbSettings;

    @Autowired
    private Environment env;


    @GetMapping("/hello")
    public String sayHello(){
        return greetingMessage+ " : "+ dbSettings.getConnection();
    }

    @GetMapping("/envdet")
    public String envDetails(){
        return env.toString();
    }

}
