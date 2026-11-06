package com.example.marketPlace.controller.DTO;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timeStamp,
                            int status,
                            String error,
                            String message,
                            String path) {
}