package com.maniknr.livechat_backend.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatMessage {
    private String message;

    private String sender;

    private MessageType messageType;

    public enum MessageType {CHAT,JOIN,LEAVE}

}
