package com.example.JWTImplementation.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomePage {
    @GetMapping("/get")
    public String welcome(){
        String welcomeMessage = "This is a private page.";
        System.out.println();
        welcomeMessage += "This page is not allowed to access for the" +
                "unauthorized users.";
        return welcomeMessage;
    }
}
