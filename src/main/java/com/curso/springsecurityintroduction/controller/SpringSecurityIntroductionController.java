package com.curso.springsecurityintroduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecurityIntroductionController {

    @GetMapping(path = "/home")
    public String home(){
        return "beer";
    }

    @GetMapping(path = {"/","/login"})
    public String login(){
        return "login";
    }
}
