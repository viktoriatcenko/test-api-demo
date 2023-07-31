package ru.maxima.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1")
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from HelloController";
    }
}
