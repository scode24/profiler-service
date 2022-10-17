package com.codex.profiler.profilerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel {

    private String message;
    private String additionalInfo;
}
