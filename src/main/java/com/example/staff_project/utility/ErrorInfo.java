package com.example.staff_project.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorInfo {
    private int errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;
}
