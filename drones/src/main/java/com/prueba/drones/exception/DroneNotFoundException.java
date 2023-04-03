package com.prueba.drones.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class DroneNotFoundException extends RuntimeException {
    private List<String> errorMessages;

    public DroneNotFoundException() {
        super();
    }

    public DroneNotFoundException(String message) {
        super(message);
    }

    public DroneNotFoundException(List<String> errorMessages) {
        super("Drone Not Found");
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
