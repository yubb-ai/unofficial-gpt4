package com.gpt4.copilot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yangyang
 * @create 2024-04-26 14:15
 */

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Embeddings {
    private Object input;
    private String model;
}
