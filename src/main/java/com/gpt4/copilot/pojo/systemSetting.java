package com.gpt4.copilot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yangyang
 * @create 2024-02-16 0:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class systemSetting {
    /**
     * server.port
     */
    private Integer serverPort;

    /**
     * server.servlet.context-path
     */
    private String prefix;
    /**
     * gpt4 sleep time
     */
    private Integer gpt4_sleepTime;

    /**
     * gpt3 sleep time
     */
    private Integer gpt3_sleepTime;

    /**
     * changeSleepTime's password
     */
    private String password;

    /**
     * self-define get_token_url
     */
    private String get_token_url;
}