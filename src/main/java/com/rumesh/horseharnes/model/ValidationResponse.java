package com.rumesh.horseharnes.model;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResponse {
    private int lineNo;
    private Insights insights;
    private SectionalDataRecord record;
    private List<ResponseMessage> messages;

    @Override
    public String toString() {
        return "{" +
                "lineNo:" + lineNo +
                ", record:" + record +
                ", messages:" + messages +
                '}';
    }
}
