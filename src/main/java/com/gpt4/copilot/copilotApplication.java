package com.gpt4.copilot;


import com.gpt4.copilot.pojo.systemSetting;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author YANGYANG
 */

/**
 * 定时注解
 *
 * @author YANGYANG
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class copilotApplication {
    public static void main(String[] args) {
        String configFilePath = System.getProperty("user.dir") + File.separator + "config.json";
        systemSetting config = loadConfig(configFilePath);
        setSystemProperties(config);
        SpringApplication.run(copilotApplication.class, args);
        printStartupMessage(config);
    }

    private static systemSetting loadConfig(String configFilePath) {
        File jsonFile = new File(configFilePath);
        if (!jsonFile.exists()) {
            createEmptyConfigFile(configFilePath);
        }
        JSONObject jsonObject = readJsonFile(configFilePath);
        // 将修改后的 JSONObject 转换为格式化的 JSON 字符串

        return parseConfig(configFilePath, jsonObject);
    }

    private static void createEmptyConfigFile(String configFilePath) {
        try {
            Files.writeString(Paths.get(configFilePath), "{}");
            System.out.println("config.json创建完成: " + configFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONObject readJsonFile(String configFilePath) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(configFilePath)));
            return new JSONObject(jsonContent);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static systemSetting parseConfig(String configFilePath, JSONObject jsonObject) {
        try {
            systemSetting config = new systemSetting();
            config.setServerPort(getIntOrDefault(jsonObject, "serverPort", 8080));
            config.setPrefix(getStringOrDefault(jsonObject, "prefix", "/"));
            config.setGpt4_sleepTime(getIntOrDefault(jsonObject, "gpt4_sleepTime", 100));
            config.setGpt3_sleepTime(getIntOrDefault(jsonObject, "gpt3_sleepTime", 0));
            config.setPassword(getStringOrDefault(jsonObject, "password", UUID.randomUUID().toString()));
            config.setGet_token_url(getStringOrDefault(jsonObject, "get_token_url", "https://api.copilot.org/copilot_internal/v2/token"));
            String updatedJson = jsonObject.toString(2);
            Files.write(Paths.get(configFilePath), updatedJson.getBytes());
            return config;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getIntOrDefault(JSONObject jsonObject, String key, int defaultValue) {
        try {
            if (!jsonObject.has(key)) {
                jsonObject.put(key, defaultValue);
                log.info("config.json没有新增" + key + "参数,现已增加！");
            }
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getStringOrDefault(JSONObject jsonObject, String key, String defaultValue) {
        try {
            if (!jsonObject.has(key)) {
                jsonObject.put(key, defaultValue);
                log.info("config.json没有新增" + key + "参数,现已增加！");
            }
            return jsonObject.getString(key);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setSystemProperties(systemSetting config) {
        System.setProperty("server.port", String.valueOf(config.getServerPort()));
        System.setProperty("server.servlet.context-path", config.getPrefix());
    }

    private static void printStartupMessage(systemSetting config) {
        System.out.println("-------------------------------------------------------");
        System.out.println("------原神gpt4-copilot-java-native v0.0.4启动成功--------");
        System.out.println("* 采用graalvm打包，运行内存大幅度减小");
        System.out.println("* 增加自定义获取token渠道");
        System.out.println("* 增加自定义/self/*接口");
        System.out.println("* 增加反代/copilot_internal/v2/token接口");
        System.out.println("URL地址：http://0.0.0.0:" + config.getServerPort() + config.getPrefix() + "");
        System.out.println("-------------------------------------------------------\n");
        System.out.println("---------------------配置说明---------------------------");
        System.out.println("gpt4_sleepTime：" + config.getGpt4_sleepTime());
        System.out.println("gpt3_sleepTime：" + config.getGpt3_sleepTime());
        System.out.println("get_token_url：" + config.getGet_token_url());
        System.out.println("password：" + config.getPassword());
        System.out.println("初始化接口成功！");
        System.out.println("-------------------------------------------------------");
    }


}

