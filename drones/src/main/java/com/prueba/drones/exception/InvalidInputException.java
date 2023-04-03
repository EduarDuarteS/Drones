
package com.prueba.drones.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

//import com.prueba.drones.enums.DroneError;

public class InvalidInputException extends RuntimeException {
    private List<String> errorMessages;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(List<String> errorMessages) {
        super("Invalid input");
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
