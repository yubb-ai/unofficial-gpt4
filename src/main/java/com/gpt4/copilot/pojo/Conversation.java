package com.gpt4.copilot.pojo;

import com.unfbx.chatgpt.entity.chat.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YANGYANG
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {

    private String model;

    private List<Message> messages;

    private Boolean stream;

}
