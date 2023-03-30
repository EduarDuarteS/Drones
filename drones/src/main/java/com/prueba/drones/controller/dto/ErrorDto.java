package com.prueba.drones.controller.dto;

public class ErrorDto {
    private String message;
    private String code;

    public ErrorDto(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
