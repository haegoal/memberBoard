package com.example.memberboard.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("/error/404")
    public String error(){
        return "error";
    }
}
