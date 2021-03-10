package com.transformers.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseModel {

    private final String errorMessage;
    private final LocalDateTime timestamp;
    private final String path;
}