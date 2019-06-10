package com.ricky.sb.controller;

import com.ricky.sb.config.WebServiceConfig;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@ServletComponentScan
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World! ricky庄";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}