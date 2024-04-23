package com.gpt4.copilot.pojo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.ModelType;
import com.unfbx.chatgpt.entity.chat.BaseChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;


/**
 * @author YANGYANG
 */
@Data
@SuperBuilder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Conversation implements Serializable {

    private static final Map<String, Encoding> modelMap = new HashMap();
    private static final EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();

    static {
        ModelType[] var0 = ModelType.values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            ModelType modelType = var0[var2];
            modelMap.put(modelType.getName(), registry.getEncodingForModel(modelType));
        }

        modelMap.put(BaseChatCompletion.Model.GPT_3_5_TURBO_0301.getName(), registry.getEncodingForModel(ModelType.GPT_3_5_TURBO));
        modelMap.put(BaseChatCompletion.Model.GPT_3_5_TURBO_0613.getName(), registry.getEncodingForModel(ModelType.GPT_3_5_TURBO));
        modelMap.put(BaseChatCompletion.Model.GPT_3_5_TURBO_16K.getName(), registry.getEncodingForModel(ModelType.GPT_3_5_TURBO));
        modelMap.put(BaseChatCompletion.Model.GPT_3_5_TURBO_16K_0613.getName(), registry.getEncodingForModel(ModelType.GPT_3_5_TURBO));
        modelMap.put(BaseChatCompletion.Model.GPT_3_5_TURBO_1106.getName(), registry.getEncodingForModel(ModelType.GPT_3_5_TURBO));
        modelMap.put(BaseChatCompletion.Model.GPT_4_32K.getName(), registry.getEncodingForModel(ModelType.GPT_4));
        modelMap.put(BaseChatCompletion.Model.GPT_4_32K_0314.getName(), registry.getEncodingForModel(ModelType.GPT_4));
        modelMap.put(BaseChatCompletion.Model.GPT_4_0314.getName(), registry.getEncodingForModel(ModelType.GPT_4));
        modelMap.put(BaseChatCompletion.Model.GPT_4_0613.getName(), registry.getEncodingForModel(ModelType.GPT_4));
        modelMap.put(BaseChatCompletion.Model.GPT_4_32K_0613.getName(), registry.getEncodingForModel(ModelType.GPT_4));
        modelMap.put(BaseChatCompletion.Model.GPT_4_1106_PREVIEW.getName(), registry.getEncodingForModel(ModelType.GPT_4));
        modelMap.put(BaseChatCompletion.Model.GPT_4_VISION_PREVIEW.getName(), registry.getEncodingForModel(ModelType.GPT_4));
    }

    /**
     * 是否流式输出.
     * default:false
     *
     * @see com.unfbx.chatgpt.OpenAiStreamClient
     */
    @Builder.Default
    private boolean stream = false;
    /**
     * 问题描述
     */
    @NonNull
    private List<Message> messages;
    @Builder.Default
    private String model = "gpt-3.5-turbo";


    public static Encoding getEncoding(@NotNull String modelName) {
        return modelMap.get(modelName);
    }

    public static int tokens(@NotNull Encoding enc, String text) {
        return encode(enc, text).size();
    }

    public static List encode(@NotNull Encoding enc, String text) {
        return StrUtil.isBlank(text) ? new ArrayList() : enc.encode(text);
    }

    public static int tokens(@NotNull String modelName, @NotNull List<Message> messages) {
        Encoding encoding = getEncoding(modelName);
        int tokensPerMessage = 0;
        int tokensPerName = 0;
        if (!modelName.equals(BaseChatCompletion.Model.GPT_3_5_TURBO_0613.getName()) && !modelName.equals(BaseChatCompletion.Model.GPT_3_5_TURBO_16K_0613.getName()) && !modelName.equals(BaseChatCompletion.Model.GPT_4_0314.getName()) && !modelName.equals(BaseChatCompletion.Model.GPT_4_32K_0314.getName()) && !modelName.equals(BaseChatCompletion.Model.GPT_4_0613.getName()) && !modelName.equals(BaseChatCompletion.Model.GPT_4_32K_0613.getName())) {
            if (modelName.equals(BaseChatCompletion.Model.GPT_3_5_TURBO_0301.getName())) {
                tokensPerMessage = 4;
                tokensPerName = -1;
            } else if (modelName.contains(BaseChatCompletion.Model.GPT_3_5_TURBO.getName())) {
                log.warn("Warning: gpt-3.5-turbo may update over time. Returning num tokens assuming gpt-3.5-turbo-0613.");
                tokensPerMessage = 3;
                tokensPerName = 1;
            } else if (modelName.contains(BaseChatCompletion.Model.GPT_4.getName())) {
                log.warn("Warning: gpt-4 may update over time. Returning num tokens assuming gpt-4-0613.");
                tokensPerMessage = 3;
                tokensPerName = 1;
            } else {
                log.warn("不支持的model {}. See https://github.com/openai/openai-python/blob/main/chatml.md 更多信息.", modelName);
            }
        } else {
            tokensPerMessage = 3;
            tokensPerName = 1;
        }

        int sum = 0;
        Iterator var6 = messages.iterator();

        while (var6.hasNext()) {
            Message msg = (Message) var6.next();
            sum += tokensPerMessage;
            sum += tokens(encoding, msg.getContent());
            sum += tokens(encoding, msg.getRole());
            sum += tokens(encoding, msg.getName());
            if (StrUtil.isNotBlank(msg.getName())) {
                sum += tokensPerName;
            }
        }

        sum += 3;
        return sum;
    }

    /**
     * 获取当前参数的tokens数
     */
    public long tokens() {
        if (CollectionUtil.isEmpty(this.messages) || StrUtil.isBlank(this.getModel())) {
            log.warn("参数异常model：{}，prompt：{}", this.getModel(), this.messages);
            return 0;
        }
        String temModel = this.getModel() == null || !model.startsWith("gpt-4") ? "gpt-3.5-turbo-0613" : "gpt-4-0613";
        return tokens(temModel, this.messages);
    }
}