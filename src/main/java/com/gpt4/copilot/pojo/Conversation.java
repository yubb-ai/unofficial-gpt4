package com.gpt4.copilot.pojo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.utils.TikTokensUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 描述： chat模型参数
 *
 * @author https:www.unfbx.com
 * 2023-03-02
 */
@Data
@SuperBuilder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Conversation implements Serializable {

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

    /**
     * 获取当前参数的tokens数
     */
    public long tokens() {
        if (CollectionUtil.isEmpty(this.messages) || StrUtil.isBlank(this.getModel())) {
            log.warn("参数异常model：{}，prompt：{}", this.getModel(), this.messages);
            return 0;
        }
        String temModel = this.getModel() == null || !model.startsWith("gpt-4") ? "gpt-3.5-turbo" :"gpt-4-0613";
        return TikTokensUtil.tokens(temModel, this.messages);
    }

    @Builder.Default
    private String model = "gpt-3.5-turbo";
}