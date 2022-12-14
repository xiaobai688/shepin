package com.example.shepin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
public class TestController {

    @GetMapping("/hello/query")
    public void hello(){
        System.out.println(LocalDateTime.now().getHour());
    }

}
