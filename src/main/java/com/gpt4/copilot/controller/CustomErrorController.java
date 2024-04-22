package com.gpt4.copilot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yangyang
 * @create 2024-02-05 1:18
 */
@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity<String> error() {
        return new ResponseEntity<>("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <p>Thanks you use gpt4-copilot-java-0.2.3</p>\n" +
                "    <p><a href=\"https://apifox.com/apidoc/shared-4301e565-a8df-48a0-85a5-bda2c4c3965a\">详细使用文档</a></p>\n" +
                "    <p><a href=\"https://github.com/Yanyutin753/unofficial-gpt4-api\">项目地址</a></p>\n" +
                "</body>\n" +
                "</html>\n", HttpStatus.OK);
    }
}