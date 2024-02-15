package com.gpt4.copilot;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author YANGYANG
 */

/**
 * 定时注解
 * @author YANGYANG
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class copilotApplication {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("${server.port}")
    private String serverPort;


    public static void main(String[] args) {
        SpringApplication.run(copilotApplication.class, args);
    }

    @PostConstruct
    public void initialize() {
        System.out.println("------------------------------------------------------");
        System.out.println("----------原神gpt4-copilot-java v0.0.3启动成功------------");
        System.out.println("* 优化打字机输出");
        System.out.println("* 增加自定义获取token渠道");
        System.out.println("* 增加反代/copilot_internal/v2/token接口");
        System.out.println("URL地址：http://0.0.0.0:" + serverPort + contextPath +"");
        System.out.println("------------------------------------------------------");
    }
}

