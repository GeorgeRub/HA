package com.ha.back.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class MessageResponse {

    @Getter
    @Setter
    private String message;

    public MessageResponse() {
    }

    public MessageResponse(String message) {
        this.message = message;
    }

}
