package com.gpt4.copilot.pojo;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yangyang
 * @create 2024-04-26 11:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JSONType(orders={"id","model","object","choices","created"})
public class streamResponse {
    private String id;
    private String model;
    private String object;
    private Choice choice;
    private String created;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JSONType(orders={"index", "delta", "finish_reason"})
    public static class Choice {
        private int index;
        private Delta delta;
        private String finish_reason;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delta {
        private String content;

    }
}
