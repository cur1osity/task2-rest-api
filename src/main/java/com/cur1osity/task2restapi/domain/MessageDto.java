package com.cur1osity.task2restapi.domain;

import java.util.List;

public class MessageDto {

    private List<String> messages;

    public MessageDto(List<String> messages) {
        this.messages = messages;
    }

    public MessageDto() {
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
