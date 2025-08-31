package com.example.ecommerceproject.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
@Slf4j
public class MainController {

    @GetMapping("/mainPage")
    public String mainPage() {
        log.info("GET /main/mainPage - 메인 페이지 요청");
        return "main/mainPage";
    }

}