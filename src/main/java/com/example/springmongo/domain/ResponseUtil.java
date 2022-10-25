package com.example.springmongo.domain;

import com.example.springmongo.constant.AppConstant.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static <T> ResponseEntity<Object> build(String message, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(message, data, httpStatus.isError()), httpStatus);
    }

    private static <T> ApiResponse<T> build(String message, T data, boolean error) {
        return ApiResponse.<T>builder()
                .status(ApiResponseStatus.builder()
                        .message(message)
                        .build())
                .timestamp(LocalDateTime.now())
                .data(data)
                .isError(error)
                .build();
    }


}
