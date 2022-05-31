package com.rumesh.horseharnes.model;

import com.rumesh.horseharnes.type.MessageLevel;
import lombok.Data;

@Data
public class ResponseMessage {
    private String message;
    private MessageLevel messageLevel;

    @Override
    public String toString() {
        return "{" +
                "message:'" + message + '\'' +
                ", messageLevel:" + messageLevel +
                '}';
    }
}
