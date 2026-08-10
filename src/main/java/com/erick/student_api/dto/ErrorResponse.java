package com.erick.student_api.dto;

import java.time.LocalDateTime;
import java.util.Map;


public record ErrorResponse (

    // Fields
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    Map<String, String> errors
) {}
