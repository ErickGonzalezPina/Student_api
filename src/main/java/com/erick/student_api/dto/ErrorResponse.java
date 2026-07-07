package com.erick.student_api.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    // Fields
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private Map<String, String> errors;

    // Constructor
    public ErrorResponse(
            LocalDateTime timestamp, int status, String error,
            String message, Map<String, String> errors) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }

}
