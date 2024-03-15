package com.gpt4.copilot;


import com.gpt4.copilot.controller.ChatController;
import com.gpt4.copilot.pojo.SystemSetting;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    private static final String VS_CODE_API_URL = "https://api.github.com/repos/microsoft/vscode/releases/latest";
    private static final String VS_CODE_CHAT_URL = "https://marketplace.visualstudio.com/_apis/public/gallery/extensionquery";

    public static void main(String[] args) throws Exception {
        String configFilePath = System.getProperty("user.dir") + File.separator + "config.json";
        SystemSetting config = loadConfig(configFilePath);
        setSystemProperties(config);
        SpringApplication.run(copilotApplication.class, args);
        printStartupMessage(config);
    }

    private static SystemSetting loadConfig(String configFilePath) {
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

    private static SystemSetting parseConfig(String configFilePath, JSONObject jsonObject) {
        try {
            SystemSetting config = new SystemSetting();
            config.setServerPort(getIntOrDefault(jsonObject, "serverPort", 8080));
            config.setPrefix(getStringOrDefault(jsonObject, "prefix", "/"));
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

    private static void setSystemProperties(SystemSetting config) {
        System.setProperty("server.port", String.valueOf(config.getServerPort()));
        System.setProperty("server.servlet.context-path", config.getPrefix());
    }

    public static String getLatestVSCodeVersion() {
        try {
            URL url = new URL(VS_CODE_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();
            JSONObject jsonObject = new JSONObject(response.toString());
            return jsonObject.getString("tag_name");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLatestExtensionVersion(String publisher, String name) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(VS_CODE_CHAT_URL).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json;api-version=6.1-preview.1");
            conn.setDoOutput(true);
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("filters", new JSONArray().put(new JSONObject().put("criteria", new JSONArray().put(new JSONObject().put("filterType", 7).put("value", publisher + "." + name)))));
            jsonRequest.put("flags", 870);
            OutputStream os = conn.getOutputStream();
            os.write(jsonRequest.toString().getBytes());
            os.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONArray("results").getJSONObject(0).getJSONArray("extensions").getJSONObject(0).getJSONArray("versions").getJSONObject(0).getString("version");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0 0 3 1/3 * ?")
    private static void updateLatestVersion() {
        try {
            String latestVersion = getLatestVSCodeVersion();
            String latestChatVersion = getLatestExtensionVersion("GitHub", "copilot-chat");
            if (latestVersion != null && latestChatVersion != null) {
                ChatController.setVscode_version(latestVersion);
                ChatController.setCopilot_chat_version("copilot-chat/" + latestChatVersion);
            }
            String parent = ChatController.selectFile();
            // 读取 JSON 文件内容
            String jsonContent = new String(Files.readAllBytes(Paths.get(parent)));
            JSONObject jsonObject = new JSONObject(jsonContent);
            jsonObject.put("vscode_version", latestVersion);
            jsonObject.put("copilot_chat_version", latestChatVersion);
            // 将修改后的 JSONObject 转换为格式化的 JSON 字符串
            String updatedJson = jsonObject.toString(2);
            Files.write(Paths.get(parent), updatedJson.getBytes());
            System.out.println("===================配置更新说明========================");
            System.out.println("vscode_version：" + ChatController.getVscode_version());
            System.out.println("copilot_chat_version：" + ChatController.getCopilot_chat_version());
            System.out.println("======================================================");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void printStartupMessage(SystemSetting config) {
        System.out.println("\n=====================配置说明==========================");
        System.out.println("serverPort：" + config.getServerPort());
        System.out.println("prefix：" + config.getPrefix());
        System.out.println("password：" + ChatController.getPassword());
        System.out.println("maxPoolSize：" + ChatController.getMaxPoolSize());
        System.out.println("gpt3_sleepTime：" + ChatController.getGpt3_sleepTime());
        System.out.println("gpt4_sleepTime：" + ChatController.getGpt4_sleepTime());
        System.out.println("vscode_version：" + ChatController.getVscode_version());
        System.out.println("copilot_chat_version：" + ChatController.getCopilot_chat_version());
        System.out.println("get_token_url：" + ChatController.getGet_token_url());
        System.out.println("one_copilot_limit：" + ChatController.getOne_copilot_limit());
        System.out.println("one_coCopilot_limit：" + ChatController.getOne_coCopilot_limit());
        System.out.println("one_selfCopilot_limit：" + ChatController.getOne_selfCopilot_limit());
        System.out.println("gpt4-copilot-java 初始化接口成功！");
        System.out.println("======================================================");
        System.out.println("******原神gpt4-copilot-java-native v0.0.7启动成功******");
        System.out.println("* 采用graalvm打包，运行内存大幅度减小");
        System.out.println("* 适配官方requestBody,减小被查询异常");
        System.out.println("* 使用ConcurrentHashMap，粗略的对于每个密钥按每分钟进行限速");
        System.out.println("URL地址：http://0.0.0.0:" + config.getServerPort() + config.getPrefix() + "");
        System.out.println("======================================================");
    }
}

