package com.gpt4.copilot.controller;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpt4.copilot.pojo.Result;
import com.gpt4.copilot.pojo.systemSetting;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yangyang
 * @create 2023-12-25 18:29
 */

@Slf4j
@RestController()
public class chatController {
    /**
     * 缓存cocopilotToken
     */
    private static final HashMap<String, String> copilotTokenList;
    /**
     * 缓存copilotToken
     */
    private static final HashMap<String, String> coCopilotTokenList;
    /**
     * 缓存selfToken
     */
    private static final HashMap<String, String> selfTokenList;
    /**
     * 模型
     */
    private static final String models = "{\"data\":[{\"id\":\"text-search-babbage-doc-001\",\"object\":\"model\",\"created\":1651172509,\"owned_by\":\"openai-dev\"},{\"id\":\"gpt-4\",\"object\":\"model\",\"created\":1687882411,\"owned_by\":\"openai\"},{\"id\":\"babbage\",\"object\":\"model\",\"created\":1649358449,\"owned_by\":\"openai\"},{\"id\":\"gpt-3.5-turbo-0613\",\"object\":\"model\",\"created\":1686587434,\"owned_by\":\"openai\"},{\"id\":\"text-babbage-001\",\"object\":\"model\",\"created\":1649364043,\"owned_by\":\"openai\"},{\"id\":\"gpt-3.5-turbo\",\"object\":\"model\",\"created\":1677610602,\"owned_by\":\"openai\"},{\"id\":\"gpt-3.5-turbo-1106\",\"object\":\"model\",\"created\":1698959748,\"owned_by\":\"system\"},{\"id\":\"curie-instruct-beta\",\"object\":\"model\",\"created\":1649364042,\"owned_by\":\"openai\"},{\"id\":\"gpt-3.5-turbo-0301\",\"object\":\"model\",\"created\":1677649963,\"owned_by\":\"openai\"},{\"id\":\"gpt-3.5-turbo-16k-0613\",\"object\":\"model\",\"created\":1685474247,\"owned_by\":\"openai\"},{\"id\":\"text-embedding-ada-002\",\"object\":\"model\",\"created\":1671217299,\"owned_by\":\"openai-internal\"},{\"id\":\"davinci-similarity\",\"object\":\"model\",\"created\":1651172509,\"owned_by\":\"openai-dev\"},{\"id\":\"curie-similarity\",\"object\":\"model\",\"created\":1651172510,\"owned_by\":\"openai-dev\"},{\"id\":\"babbage-search-document\",\"object\":\"model\",\"created\":1651172510,\"owned_by\":\"openai-dev\"},{\"id\":\"curie-search-document\",\"object\":\"model\",\"created\":1651172508,\"owned_by\":\"openai-dev\"},{\"id\":\"babbage-code-search-code\",\"object\":\"model\",\"created\":1651172509,\"owned_by\":\"openai-dev\"},{\"id\":\"ada-code-search-text\",\"object\":\"model\",\"created\":1651172510,\"owned_by\":\"openai-dev\"},{\"id\":\"text-search-curie-query-001\",\"object\":\"model\",\"created\":1651172509,\"owned_by\":\"openai-dev\"},{\"id\":\"text-davinci-002\",\"object\":\"model\",\"created\":1649880484,\"owned_by\":\"openai\"},{\"id\":\"ada\",\"object\":\"model\",\"created\":1649357491,\"owned_by\":\"openai\"},{\"id\":\"text-ada-001\",\"object\":\"model\",\"created\":1649364042,\"owned_by\":\"openai\"},{\"id\":\"ada-similarity\",\"object\":\"model\",\"created\":1651172507,\"owned_by\":\"openai-dev\"},{\"id\":\"code-search-ada-code-001\",\"object\":\"model\",\"created\":1651172507,\"owned_by\":\"openai-dev\"},{\"id\":\"text-similarity-ada-001\",\"object\":\"model\",\"created\":1651172505,\"owned_by\":\"openai-dev\"},{\"id\":\"text-davinci-edit-001\",\"object\":\"model\",\"created\":1649809179,\"owned_by\":\"openai\"},{\"id\":\"code-davinci-edit-001\",\"object\":\"model\",\"created\":1649880484,\"owned_by\":\"openai\"},{\"id\":\"text-search-curie-doc-001\",\"object\":\"model\",\"created\":1651172509,\"owned_by\":\"openai-dev\"},{\"id\":\"text-curie-001\",\"object\":\"model\",\"created\":1649364043,\"owned_by\":\"openai\"},{\"id\":\"curie\",\"object\":\"model\",\"created\":1649359874,\"owned_by\":\"openai\"},{\"id\":\"davinci\",\"object\":\"model\",\"created\":1649359874,\"owned_by\":\"openai\"}]}";
    private static final String machineId;
    private final static String get_cocopilotToken_url = "https://api.cocopilot.org/copilot_internal/v2/token";

    /**
     * gpt4单字符睡眠时间
     */
    private static Integer gpt4_sleepTime;

    /**
     * gpt3单字符睡眠时间
     */
    private static Integer gpt3_sleepTime;

    /**
     * 修改睡眠时间密码
     */
    private static String password;
    /**
     * 自定义获取token_url
     */
    private static String get_token_url;

    static {
        selfTokenList = new HashMap<>();
        copilotTokenList = new HashMap<>();
        coCopilotTokenList = new HashMap<>();
        machineId = generateMachineId();
        systemSetting systemSetting = selectSetting();
        setGpt4_sleepTime(systemSetting.getGpt4_sleepTime());
        setGpt3_sleepTime(systemSetting.getGpt3_sleepTime());
        setPassword(systemSetting.getPassword());
        setGet_token_url(systemSetting.getGet_token_url());
        log.info("初始化接口成功！");
    }

    private final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();
    private final ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "chatThreadPool-" + counter.getAndIncrement());
        }
    };
    private final ExecutorService executor = new ThreadPoolExecutor(0, 300, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
    private final String github_get_token_url = "https://api.github.com/copilot_internal/v2/token";
    private final String github_chat = "https://api.githubcopilot.com/chat/completions";
    private final String github_embaddings = "https://api.githubcopilot.com/embeddings";
    private final String vscode_version = "vscode/1.85.2";

    public static String selectFile() {
        String projectRoot = System.getProperty("user.dir");
        String parent = projectRoot + File.separator + "config.json";
        File jsonFile = new File(parent);
        Path jsonFilePath = Paths.get(parent);
        // 如果 JSON 文件不存在，创建一个新的 JSON 对象
        if (!jsonFile.exists()) {
            try {
                // 创建文件config.json
                Files.createFile(jsonFilePath);
                // 往 config.json 文件中添加一个空数组，防止重启报错
                Files.writeString(jsonFilePath, "{}");
                System.out.println("空数组添加完成");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("config.json创建完成: " + jsonFilePath);
        }
        return parent;
    }


    public static void setGpt4_sleepTime(Integer gpt4_sleepTime) {
        chatController.gpt4_sleepTime = gpt4_sleepTime;
    }


    public static void setGpt3_sleepTime(Integer gpt3_sleepTime) {
        chatController.gpt3_sleepTime = gpt3_sleepTime;
    }


    public static void setGet_token_url(String get_token_url) {
        chatController.get_token_url = get_token_url;
    }

    /**
     * 查询config.json里的系统值
     *
     * @return systemSettings类
     */
    public static systemSetting selectSetting() {
        boolean exist = true;
        String parent = selectFile();
        try {
            // 读取 JSON 文件内容
            String jsonContent = new String(Files.readAllBytes(Paths.get(parent)));
            // 将 JSON 字符串解析为 JSONObject
            JSONObject jsonObject = new JSONObject(jsonContent);
            try {
                jsonObject.getInt("gpt4_sleepTime");
            } catch (JSONException e) {
                jsonObject.put("gpt4_sleepTime", "100");
                log.info("config.json没有新增gpt4_sleepTime参数,现已增加！");
                exist = false;
            }
            try {
                jsonObject.getString("gpt3_sleepTime");
            } catch (JSONException e) {
                jsonObject.put("gpt3_sleepTime", "0");
                log.info("config.json没有新增gpt3_sleepTime参数,现已增加！");
                exist = false;
            }
            try {
                jsonObject.getString("password");
            } catch (JSONException e) {
                jsonObject.put("password", UUID.randomUUID().toString());
                log.info("config.json没有新增password参数,现已增加！");
                exist = false;
            }

            try {
                jsonObject.getString("get_token_url");
            } catch (JSONException e) {
                jsonObject.put("get_token_url", "https://api.cocopilot.org/copilot_internal/v2/token");
                log.info("config.json没有新增get_token_url参数,现已增加！");
                exist = false;
            }

            // 将 JSONObject 转换为 Config 类的实例
            systemSetting config = new systemSetting();
            config.setGpt4_sleepTime(jsonObject.optInt("gpt4_sleepTime"));
            config.setGpt3_sleepTime(jsonObject.optInt("gpt3_sleepTime"));
            config.setPassword(jsonObject.optString("password"));
            config.setGet_token_url(jsonObject.optString("get_token_url"));

            if (exist == false) {
                // 将修改后的 JSONObject 转换为格式化的 JSON 字符串
                String updatedJson = jsonObject.toString(2);
                Files.write(Paths.get(parent), updatedJson.getBytes());
            }
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateMachineId() {
        try {
            UUID uuid = UUID.randomUUID();
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(uuid.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPassword(String password) {
        chatController.password = password;
    }

    /**
     * 修改sleep时间
     */
    @GetMapping(value = "/changeSettings")
    private Result changeSleepTime(@RequestParam("gpt3_sleepTime") Integer gpt3_sleepTime,
                                   @RequestParam("gpt4_sleepTime") Integer gpt4_sleepTime,
                                   @RequestParam("get_token_url") String get_token_url,
                                   @RequestParam("password") String password) {
        try {
            if (password.equals(selectSetting().getPassword())) {
                String parent = selectFile();
                // 读取 JSON 文件内容
                String jsonContent = new String(Files.readAllBytes(Paths.get(parent)));
                JSONObject jsonObject = new JSONObject(jsonContent);
                if (gpt3_sleepTime != null && gpt4_sleepTime >= 0 && gpt4_sleepTime <= 150) {
                    setGpt3_sleepTime(gpt3_sleepTime);
                    jsonObject.put("gpt3_sleepTime", gpt3_sleepTime);
                }
                if (gpt4_sleepTime != null && gpt4_sleepTime >= 0 && gpt4_sleepTime <= 150) {
                    setGpt4_sleepTime(gpt4_sleepTime);
                    jsonObject.put("gpt4_sleepTime", gpt4_sleepTime);
                }
                if (get_token_url != null && get_token_url.startsWith("http")) {
                    setGet_token_url(get_token_url);
                    jsonObject.put("get_token_url", get_token_url);
                }
                // 将修改后的 JSONObject 转换为格式化的 JSON 字符串
                String updatedJson = jsonObject.toString(2);
                Files.write(Paths.get(parent), updatedJson.getBytes());
                return Result.success("修改成功！");
            } else {
                return Result.error("管理员密码不对，请重新再试！");
            }
        } catch (Exception e) {
            return Result.error("出错了，修改失败！");
        }
    }

    /**
     * 请求体不是json
     * Authorization token缺失  会报Authorization header is missing
     * 无法请求到chat_token 会报github copilot APIKey is wrong
     * 反代/copilot_internal/v2/token 接口
     *
     * @param request
     * @return
     * @throws JSONException
     * @throws IOException
     */
    @PostMapping(value = "/copilot_internal/v2/token")
    public ResponseEntity<Object> getV2Token(HttpServletResponse response, HttpServletRequest request) {
        String authorizationHeader = StringUtils.trimToNull(request.getHeader("Authorization"));
        CompletableFuture<ResponseEntity<Object>> future = CompletableFuture.supplyAsync(() -> {
            try {
                String apiKey;
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    apiKey = authorizationHeader.substring(7);
                } else {
                    return new ResponseEntity<>(Result.error("Authorization header is missing"), HttpStatus.UNAUTHORIZED);
                }
                Request request_token = new Request.Builder().url(github_get_token_url)
                        .addHeader("Host", "api.github.com")
                        .addHeader("authorization", "token " + apiKey)
                        .addHeader("Editor-Version", vscode_version)
                        .addHeader("Editor-Plugin-Version", "copilot-chat/0.11.1")
                        .addHeader("User-Agent", "GitHubCopilotChat/0.11.1")
                        .addHeader("Accept", "*/*").build();
                try (Response res = client.newCall(request_token).execute()) {
                    log.info(res.toString());
                    if (!res.isSuccessful()) {
                        return new ResponseEntity<>(Result.error("Unsuccessful response: " + res.message()), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    response.setContentType("application/json; charset=utf-8");
                    OutputStream out = new BufferedOutputStream(response.getOutputStream());
                    InputStream in = new BufferedInputStream(res.body().byteStream());
                    // 一次拿多少数据 迭代循环
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                        out.flush();
                    }
                    return new ResponseEntity<>(null, HttpStatus.OK);
                }
            } catch (IOException e) {
                throw new RuntimeException("IO Error: " + e.getMessage(), e);
            }
        }, executor);

        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = future.get(6, TimeUnit.MINUTES);
        } catch (TimeoutException ex) {
            future.cancel(true);
            responseEntity = new ResponseEntity<>(Result.error("The task timed out"), HttpStatus.REQUEST_TIMEOUT);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(Result.error("An error occurred: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 请求体不是json 会报Request body is missing or not in JSON format
     * Authorization token缺失  会报Authorization header is missing
     * 无法请求到chat_token 会报copilot APIKey is wrong
     *
     * @param response
     * @param request
     * @param conversation
     * @return
     * @throws JSONException
     * @throws IOException
     */
    @PostMapping(value = "/v1/chat/completions")
    public ResponseEntity<Object> coPilotConversation(HttpServletResponse response, HttpServletRequest request, @org.springframework.web.bind.annotation.RequestBody Object conversation) {
        String authorizationHeader = StringUtils.trimToNull(request.getHeader("Authorization"));
        // 异步处理
        CompletableFuture<ResponseEntity<Object>> future = CompletableFuture.supplyAsync(() -> {
            try {
                if (conversation == null) {
                    return new ResponseEntity<>(Result.error("Request body is missing or not in JSON format"), HttpStatus.BAD_REQUEST);
                }
                String apiKey;
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    apiKey = authorizationHeader.substring(7);
                } else {
                    return new ResponseEntity<>(Result.error("Authorization header is missing"), HttpStatus.UNAUTHORIZED);
                }
                if (!copilotTokenList.containsKey(apiKey)) {
                    String token = getCopilotToken(apiKey);
                    if (token == null) {
                        return new ResponseEntity<>(Result.error("Github Copilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                    }
                    copilotTokenList.put(apiKey, token);
                    log.info("Github CopilotToken初始化成功！");
                }
                // 创建OkHttpClient请求 请求https://api.githubcopilot.com/chat/completions
                String chat_token = copilotTokenList.get(apiKey);
                Map<String, String> headersMap = new HashMap<>();
                //添加头部
                addHeader(headersMap, chat_token);
                String json = com.alibaba.fastjson2.JSON.toJSONString(conversation);
                // 创建一个 RequestBody 对象
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(json, JSON);
                Request.Builder requestBuilder = new Request.Builder().url(github_chat).post(requestBody);
                headersMap.forEach(requestBuilder::addHeader);
                Request streamRequest = requestBuilder.build();
                try (Response resp = client.newCall(streamRequest).execute()) {
                    if (!resp.isSuccessful()) {
                        if (resp.code() == 429) {
                            return new ResponseEntity<>(Result.error("rate limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                        } else {
                            String token = getCopilotToken(apiKey);
                            if (token == null) {
                                return new ResponseEntity<>(Result.error("copilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                            }
                            copilotTokenList.put(apiKey, token);
                            log.info("token过期，Github CopilotToken重置化成功！");
                            againConversation(response, conversation, token);
                        }
                    } else {
                        // 流式和非流式输出
                        outPutChat(response, resp, conversation);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }, executor);

        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = future.get(6, TimeUnit.MINUTES);
        } catch (TimeoutException ex) {
            future.cancel(true);
            responseEntity = new ResponseEntity<>(Result.error("The task timed out"), HttpStatus.REQUEST_TIMEOUT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseEntity = new ResponseEntity<>(Result.error("An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 请求体不是json 会报Request body is missing or not in JSON format
     * Authorization token缺失  会报Authorization header is missing
     * 无法请求到chat_token 会报copilot APIKey is wrong
     *
     * @param response
     * @param request
     * @param conversation
     * @return
     * @throws JSONException
     * @throws IOException
     */

    @PostMapping(value = "/cocopilot/v1/chat/completions")
    public ResponseEntity<Object> coCoPilotConversation(HttpServletResponse response, HttpServletRequest request, @org.springframework.web.bind.annotation.RequestBody Object conversation) {
        String authorizationHeader = StringUtils.trimToNull(request.getHeader("Authorization"));
        // 异步处理
        CompletableFuture<ResponseEntity<Object>> future = CompletableFuture.supplyAsync(() -> {
            try {
                if (conversation == null) {
                    return new ResponseEntity<>(Result.error("Request body is missing or not in JSON format"), HttpStatus.BAD_REQUEST);
                }
                String apiKey;
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    apiKey = authorizationHeader.substring(7);
                } else {
                    return new ResponseEntity<>(Result.error("Authorization header is missing"), HttpStatus.UNAUTHORIZED);
                }
                if (!coCopilotTokenList.containsKey(apiKey)) {
                    String token = getCoCoToken(apiKey);
                    if (token == null) {
                        return new ResponseEntity<>(Result.error("始皇 cocopilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                    }
                    coCopilotTokenList.put(apiKey, token);
                    log.info("coCopilotToken初始化成功！");
                }
                // 创建OkHttpClient请求 请求https://api.githubcopilot.com/chat/completions
                String chat_token = coCopilotTokenList.get(apiKey);
                Map<String, String> headersMap = new HashMap<>();
                //添加头部
                addHeader(headersMap, chat_token);
                String json = com.alibaba.fastjson2.JSON.toJSONString(conversation);
                // 创建一个 RequestBody 对象
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(json, JSON);
                Request.Builder requestBuilder = new Request.Builder().url(github_chat).post(requestBody);
                headersMap.forEach(requestBuilder::addHeader);
                Request streamRequest = requestBuilder.build();
                try (Response resp = client.newCall(streamRequest).execute()) {
                    if (!resp.isSuccessful()) {
                        if (resp.code() == 429) {
                            return new ResponseEntity<>(Result.error("rate limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                        } else {
                            String token = getCoCoToken(apiKey);
                            if (token == null) {
                                return new ResponseEntity<>(Result.error("cocopilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                            }
                            coCopilotTokenList.put(apiKey, token);
                            log.info("token过期，始皇coCopilotToken重置化成功！");
                            againConversation(response, conversation, token);
                        }
                    } else {
                        // 流式和非流式输出
                        outPutChat(response, resp, conversation);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }, executor);

        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = future.get(6, TimeUnit.MINUTES);
        } catch (TimeoutException ex) {
            future.cancel(true);
            responseEntity = new ResponseEntity<>(Result.error("The chat timed out"), HttpStatus.REQUEST_TIMEOUT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseEntity = new ResponseEntity<>(Result.error("An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping(value = "/self/v1/chat/completions")
    public ResponseEntity<Object> selfConversation(HttpServletResponse response, HttpServletRequest request, @org.springframework.web.bind.annotation.RequestBody Object conversation) {
        String authorizationHeader = StringUtils.trimToNull(request.getHeader("Authorization"));
        // 异步处理
        CompletableFuture<ResponseEntity<Object>> future = CompletableFuture.supplyAsync(() -> {
            try {
                if (conversation == null) {
                    return new ResponseEntity<>(Result.error("Request body is missing or not in JSON format"), HttpStatus.BAD_REQUEST);
                }
                String apiKey;
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    apiKey = authorizationHeader.substring(7);
                } else {
                    return new ResponseEntity<>(Result.error("Authorization header is missing"), HttpStatus.UNAUTHORIZED);
                }
                if (!selfTokenList.containsKey(apiKey)) {
                    String token = getSelfToken(apiKey);
                    if (token == null) {
                        return new ResponseEntity<>(Result.error("自定义self APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                    }
                    selfTokenList.put(apiKey, token);
                    log.info("自定义selfToken初始化成功！");
                }
                // 创建OkHttpClient请求 请求https://api.githubcopilot.com/chat/completions
                String chat_token = selfTokenList.get(apiKey);
                Map<String, String> headersMap = new HashMap<>();
                //添加头部
                addHeader(headersMap, chat_token);
                String json = com.alibaba.fastjson2.JSON.toJSONString(conversation);
                // 创建一个 RequestBody 对象
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(json, JSON);
                Request.Builder requestBuilder = new Request.Builder().url(github_chat).post(requestBody);
                headersMap.forEach(requestBuilder::addHeader);
                Request streamRequest = requestBuilder.build();
                try (Response resp = client.newCall(streamRequest).execute()) {
                    if (!resp.isSuccessful()) {
                        if (resp.code() == 429) {
                            return new ResponseEntity<>(Result.error("rate limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                        } else {
                            String token = getCoCoToken(apiKey);
                            if (token == null) {
                                return new ResponseEntity<>(Result.error("自定义self APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                            }
                            selfTokenList.put(apiKey, token);
                            log.info("token过期，自定义selfToken重置化成功！");
                            againConversation(response, conversation, token);
                        }
                    } else {
                        // 流式和非流式输出
                        outPutChat(response, resp, conversation);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }, executor);

        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = future.get(6, TimeUnit.MINUTES);
        } catch (TimeoutException ex) {
            future.cancel(true);
            responseEntity = new ResponseEntity<>(Result.error("The chat timed out"), HttpStatus.REQUEST_TIMEOUT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseEntity = new ResponseEntity<>(Result.error("An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 如发现token过期
     * 重新回复问题
     *
     * @param response
     * @param conversation
     * @param token
     * @return
     */
    public Object againConversation(HttpServletResponse response, @org.springframework.web.bind.annotation.RequestBody Object conversation, String token) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            //添加头部
            addHeader(headersMap, token);
            String json = JSON.toJSONString(conversation);
            // 创建一个 RequestBody 对象
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(json, JSON);
            Request.Builder requestBuilder = new Request.Builder().url(github_chat).post(requestBody);
            headersMap.forEach(requestBuilder::addHeader);
            Request streamRequest = requestBuilder.build();
            try (Response resp = client.newCall(streamRequest).execute()) {
                if (!resp.isSuccessful()) {
                    return new ResponseEntity<>("copilot/cocopilot/self APIKey is wrong Or your network is wrong", HttpStatus.UNAUTHORIZED);
                } else {
                    // 流式和非流式输出
                    outPutChat(response, resp, conversation);
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ghu/gho 请求
     * 请求体不是json 会报Request body is missing or not in JSON format
     * Authorization token缺失  会报Authorization header is missing
     * 无法请求到chat_token 会报copilot APIKey is wrong
     *
     * @param response
     * @param request
     * @param conversation
     * @return
     * @throws JSONException
     * @throws IOException
     */
    @PostMapping(value = "/v1/embeddings")
    public ResponseEntity<Object> coPilotEmbeddings(HttpServletResponse response, HttpServletRequest request, @org.springframework.web.bind.annotation.RequestBody Object conversation) {
        String authorizationHeader = StringUtils.trimToNull(request.getHeader("Authorization"));
        // 异步处理
        CompletableFuture<ResponseEntity<Object>> future = CompletableFuture.supplyAsync(() -> {
            try {
                if (conversation == null) {
                    return new ResponseEntity<>(Result.error("Request body is missing or not in JSON format"), HttpStatus.BAD_REQUEST);
                }
                String apiKey;
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    apiKey = authorizationHeader.substring(7);
                } else {
                    return new ResponseEntity<>(Result.error("Authorization header is missing"), HttpStatus.UNAUTHORIZED);
                }
                if (!copilotTokenList.containsKey(apiKey)) {
                    String token = getCopilotToken(apiKey);
                    if (token == null) {
                        return new ResponseEntity<>(Result.error("Github Copilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                    }
                    copilotTokenList.put(apiKey, token);
                    log.info("Github CopilotToken初始化成功！");
                }
                // 创建OkHttpClient请求 请求https://api.githubcopilot.com/chat/completions
                String chat_token = copilotTokenList.get(apiKey);
                Map<String, String> headersMap = new HashMap<>();
                //添加头部
                addHeader(headersMap, chat_token);
                String json = com.alibaba.fastjson2.JSON.toJSONString(conversation);
                // 创建一个 RequestBody 对象
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(json, JSON);
                Request.Builder requestBuilder = new Request.Builder().url(github_embaddings).post(requestBody);
                headersMap.forEach(requestBuilder::addHeader);
                Request streamRequest = requestBuilder.build();
                try (Response resp = client.newCall(streamRequest).execute()) {
                    if (!resp.isSuccessful()) {
                        if (resp.code() == 429) {
                            return new ResponseEntity<>(Result.error("rate limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                        } else {
                            String token = getCopilotToken(apiKey);
                            if (token == null) {
                                return new ResponseEntity<>(Result.error("Github Copilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                            }
                            copilotTokenList.put(apiKey, token);
                            log.info("token过期，Github CopilotToken重置化成功！");
                            againEmbeddings(response, conversation, token);
                        }
                    } else {
                        // 非流式输出
                        outPutEmbeddings(response, resp);
                    }
                }
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);

        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = future.get(6, TimeUnit.MINUTES);
        } catch (TimeoutException ex) {
            future.cancel(true);
            responseEntity = new ResponseEntity<>(Result.error("The chat timed out"), HttpStatus.REQUEST_TIMEOUT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseEntity = new ResponseEntity<>(Result.error("An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * ccu 请求
     * 请求体不是json 会报Request body is missing or not in JSON format
     * Authorization token缺失  会报Authorization header is missing
     * 无法请求到chat_token 会报copilot APIKey is wrong
     *
     * @param response
     * @param request
     * @param conversation
     * @return
     * @throws JSONException
     * @throws IOException
     */
    @PostMapping(value = "/cocopilot/v1/embeddings")
    public ResponseEntity<Object> coCoPilotEmbeddings(HttpServletResponse response, HttpServletRequest request, @org.springframework.web.bind.annotation.RequestBody Object conversation) {
        String authorizationHeader = StringUtils.trimToNull(request.getHeader("Authorization"));
        // 异步处理
        CompletableFuture<ResponseEntity<Object>> future = CompletableFuture.supplyAsync(() -> {
            try {
                if (conversation == null) {
                    return new ResponseEntity<>(Result.error("Request body is missing or not in JSON format"), HttpStatus.BAD_REQUEST);
                }
                String apiKey;
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    apiKey = authorizationHeader.substring(7);
                } else {
                    return new ResponseEntity<>(Result.error("Authorization header is missing"), HttpStatus.UNAUTHORIZED);
                }
                if (!coCopilotTokenList.containsKey(apiKey)) {
                    String token = getCoCoToken(apiKey);
                    if (token == null) {
                        return new ResponseEntity<>(Result.error("copilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                    }
                    coCopilotTokenList.put(apiKey, token);
                    log.info("始皇coCopilotToken初始化成功！");
                }
                // 创建OkHttpClient请求 请求https://api.githubcopilot.com/chat/completions
                String chat_token = coCopilotTokenList.get(apiKey);
                Map<String, String> headersMap = new HashMap<>();
                //添加头部
                addHeader(headersMap, chat_token);
                String json = com.alibaba.fastjson2.JSON.toJSONString(conversation);
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(json, JSON);
                Request.Builder requestBuilder = new Request.Builder().url(github_embaddings).post(requestBody);
                headersMap.forEach(requestBuilder::addHeader);
                Request streamRequest = requestBuilder.build();
                try (Response resp = client.newCall(streamRequest).execute()) {
                    if (!resp.isSuccessful()) {
                        if (resp.code() == 429) {
                            return new ResponseEntity<>(Result.error("rate limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                        } else {
                            String token = getCoCoToken(apiKey);
                            if (token == null) {
                                return new ResponseEntity<>(Result.error("copilot APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                            }
                            coCopilotTokenList.put(apiKey, token);
                            log.info("token过期，coCopilotTokenList重置化成功！");
                            againEmbeddings(response, conversation, token);
                        }
                    } else {
                        // 非流式输出
                        outPutEmbeddings(response, resp);
                    }
                }
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);

        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = future.get(6, TimeUnit.MINUTES);
        } catch (TimeoutException ex) {
            future.cancel(true);
            responseEntity = new ResponseEntity<>(Result.error("The chat timed out"), HttpStatus.REQUEST_TIMEOUT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseEntity = new ResponseEntity<>(Result.error("An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 自定义请求
     * 请求体不是json 会报Request body is missing or not in JSON format
     * Authorization token缺失  会报Authorization header is missing
     * 无法请求到chat_token 会报自定义 APIKey is wrong
     *
     * @param response
     * @param request
     * @param conversation
     * @return
     * @throws JSONException
     * @throws IOException
     */
    @PostMapping(value = "/self/v1/embeddings")
    public ResponseEntity<Object> selfEmbeddings(HttpServletResponse response, HttpServletRequest request, @org.springframework.web.bind.annotation.RequestBody Object conversation) {
        String authorizationHeader = StringUtils.trimToNull(request.getHeader("Authorization"));
        // 异步处理
        CompletableFuture<ResponseEntity<Object>> future = CompletableFuture.supplyAsync(() -> {
            try {
                if (conversation == null) {
                    return new ResponseEntity<>(Result.error("Request body is missing or not in JSON format"), HttpStatus.BAD_REQUEST);
                }
                String apiKey;
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    apiKey = authorizationHeader.substring(7);
                } else {
                    return new ResponseEntity<>(Result.error("Authorization header is missing"), HttpStatus.UNAUTHORIZED);
                }
                if (!selfTokenList.containsKey(apiKey)) {
                    String token = getSelfToken(apiKey);
                    if (token == null) {
                        return new ResponseEntity<>(Result.error("自定义APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                    }
                    selfTokenList.put(apiKey, token);
                    log.info("自定义selfToken初始化成功！");
                }
                String chat_token = selfTokenList.get(apiKey);
                Map<String, String> headersMap = new HashMap<>();
                //添加头部
                addHeader(headersMap, chat_token);
                String json = com.alibaba.fastjson2.JSON.toJSONString(conversation);
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(json, JSON);
                Request.Builder requestBuilder = new Request.Builder().url(github_embaddings).post(requestBody);
                headersMap.forEach(requestBuilder::addHeader);
                Request streamRequest = requestBuilder.build();
                try (Response resp = client.newCall(streamRequest).execute()) {
                    if (!resp.isSuccessful()) {
                        if (resp.code() == 429) {
                            return new ResponseEntity<>(Result.error("rate limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                        } else {
                            String token = getCoCoToken(apiKey);
                            if (token == null) {
                                return new ResponseEntity<>(Result.error("自定义 APIKey is wrong"), HttpStatus.UNAUTHORIZED);
                            }
                            selfTokenList.put(apiKey, token);
                            log.info("token过期，自定义selfToken重置化成功！");
                            againEmbeddings(response, conversation, token);
                        }
                    } else {
                        // 非流式输出
                        outPutEmbeddings(response, resp);
                    }
                }
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);

        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = future.get(6, TimeUnit.MINUTES);
        } catch (TimeoutException ex) {
            future.cancel(true);
            responseEntity = new ResponseEntity<>(Result.error("The chat timed out"), HttpStatus.REQUEST_TIMEOUT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseEntity = new ResponseEntity<>(Result.error("An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    public Object againEmbeddings(HttpServletResponse response, @org.springframework.web.bind.annotation.RequestBody Object conversation, String token) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            //添加头部
            addHeader(headersMap, token);
            String json = JSON.toJSONString(conversation);
            // 创建一个 RequestBody 对象
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(json, JSON);
            Request.Builder requestBuilder = new Request.Builder().url(github_embaddings).post(requestBody);
            headersMap.forEach(requestBuilder::addHeader);
            Request streamRequest = requestBuilder.build();
            try (Response resp = client.newCall(streamRequest).execute()) {
                if (!resp.isSuccessful()) {
                    return new ResponseEntity<>(Result.error("copilot/cocopilot/自定义 APIKey is wrong Or your network is wrong"), HttpStatus.UNAUTHORIZED);
                } else {
                    // 非流式输出
                    outPutEmbeddings(response, resp);
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于copilot——ghu或gho 拿到token
     *
     * @param apiKey
     * @return
     * @throws IOException
     */
    private String getCopilotToken(String apiKey) throws IOException {
        Request request = new Request.Builder().url(github_get_token_url)
                .addHeader("Host", "api.github.com")
                .addHeader("authorization", "token " + apiKey)
                .addHeader("Editor-Version", vscode_version)
                .addHeader("Editor-Plugin-Version", "copilot-chat/0.11.1")
                .addHeader("User-Agent", "GitHubCopilotChat/0.11.1")
                .addHeader("Accept", "*/*").build();
        try (Response response = client.newCall(request).execute()) {
            log.info(response.toString());
            if (!response.isSuccessful()) {
                return null;
            }
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("token") ? jsonResponse.get("token").toString() : null;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于cocopilot——ccu 拿到token
     *
     * @param apiKey
     * @return
     * @throws IOException
     */
    private String getCoCoToken(String apiKey) throws IOException {
        Request request = new Request.Builder().url(get_cocopilotToken_url)
                .addHeader("authorization", "token " + apiKey)
                .addHeader("Editor-Version", vscode_version)
                .addHeader("Editor-Plugin-Version", "copilot-chat/0.11.1")
                .addHeader("User-Agent", "GitHubCopilotChat/0.11.1")
                .addHeader("Accept", "*/*").build();
        try (Response response = client.newCall(request).execute()) {
            log.info(response.toString());
            if (!response.isSuccessful()) {
                return null;
            }
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("token") ? jsonResponse.get("token").toString() : null;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于self——ccu 拿到token
     *
     * @param apiKey
     * @return
     * @throws IOException
     */
    private String getSelfToken(String apiKey) throws IOException {
        Request request = new Request.Builder().url(get_token_url)
                .addHeader("authorization", "token " + apiKey)
                .addHeader("Editor-Version", vscode_version)
                .addHeader("Editor-Plugin-Version", "copilot-chat/0.11.1")
                .addHeader("User-Agent", "GitHubCopilotChat/0.11.1")
                .addHeader("Accept", "*/*").build();
        try (Response response = client.newCall(request).execute()) {
            log.info(response.toString());
            if (!response.isSuccessful()) {
                return null;
            }
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("token") ? jsonResponse.get("token").toString() : null;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * copilot的模型
     *
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/v1/models")
    public Object models() throws JsonProcessingException {
        try {
            Future<Object> future = executor.submit(() -> {
                String jsonString = models;
                return new ObjectMapper().readTree(jsonString);
            });
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cocopilot的模型
     *
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/cocopilot/v1/models")
    public Object cocoPilotModels() {
        try {
            Future<Object> future = executor.submit(() -> {
                String jsonString = models;
                return new ObjectMapper().readTree(jsonString);
            });
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 提问请求头
     *
     * @param headersMap
     * @param chat_token
     */
    private void addHeader(Map<String, String> headersMap, String chat_token) {
        headersMap.put("Host", "api.githubcopilot.com");
        headersMap.put("Accept-Encoding", "gzip, deflate, br");
        headersMap.put("Accept", "*/*");
        headersMap.put("Authorization", "Bearer " + chat_token);
        headersMap.put("X-Request-Id", UUID.randomUUID().toString());
        headersMap.put("X-Github-Api-Version", "2023-07-07");
        headersMap.put("Vscode-Sessionid", UUID.randomUUID().toString() + System.currentTimeMillis());
        headersMap.put("vscode-machineid", machineId);
        headersMap.put("Editor-Version", vscode_version);
        headersMap.put("Editor-Plugin-Version", "copilot-chat/0.11.1");
        headersMap.put("Openai-Organization", "github-copilot");
        headersMap.put("Copilot-Integration-Id", "vscode-chat");
        headersMap.put("Openai-Intent", "conversation-panel");
        headersMap.put("User-Agent", "GitHubCopilotChat/0.11.1");
    }

    /**
     * chat接口的输出
     *
     * @param response
     * @param resp
     * @param conversation
     */
    private void outPutChat(HttpServletResponse response, Response resp, Object conversation) {
        try {
            JSONObject jsonObject = new JSONObject(com.alibaba.fastjson2.JSON.toJSONString(conversation));
            boolean isStream = jsonObject.optBoolean("stream", false);
            String model = jsonObject.optString("model", "gpt-3.5-turbo");
            int sleep_time = calculateSleepTime(model, isStream);
            if (isStream) {
                response.setContentType("text/event-stream; charset=UTF-8");
            } else {
                response.setContentType("application/json; charset=utf-8");
            }

            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(resp.body().byteStream(), StandardCharsets.UTF_8))) {

                String line;
                while ((line = in.readLine()) != null) {
                    out.println(line);
                    out.flush();
                    if (sleep_time > 0 && line.startsWith("data")) {
                        Thread.sleep(sleep_time);
                    }
                }
                log.info("使用模型：" + model + "，字符间隔时间：" + sleep_time + "ms，响应：" + resp);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Thread was interrupted", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("IO Exception occurred", e);
        } catch (JSONException e) {
            throw new RuntimeException("JSON Exception occurred", e);
        }
    }

    /**
     * chat接口的每个字的睡眠时间
     */
    private int calculateSleepTime(String model, boolean isStream) {
        if (isStream) {
            if (!model.contains("gpt-4")) {
                return gpt3_sleepTime;
            } else {
                return gpt4_sleepTime;
            }
        } else {
            return 0;
        }
    }

    /**
     * Embeddings接口的输出
     *
     * @param response
     * @param resp
     */
    private void outPutEmbeddings(HttpServletResponse response, Response resp) {
        try {
            response.setContentType("application/json; charset=utf-8");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            InputStream in = new BufferedInputStream(resp.body().byteStream());
            // 一次拿多少数据 迭代循环
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}