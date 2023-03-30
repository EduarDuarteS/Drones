package com.prueba.drones.controller.dto;

import java.util.List;

public class ErrorResponseDto {
    private List<ErrorDto> errors;

    public ErrorResponseDto(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }
}
