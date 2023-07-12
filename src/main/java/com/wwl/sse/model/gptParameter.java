package com.wwl.sse.model;

import java.util.List;

/**
 * @author wwl
 */
public class gptParameter extends GptBaseInfo {

    private List<gptRole> messages;

    public List<gptRole> getMessages() {
        return messages;
    }

    public void setMessages(List<gptRole> messages) {
        this.messages = messages;
    }
}
