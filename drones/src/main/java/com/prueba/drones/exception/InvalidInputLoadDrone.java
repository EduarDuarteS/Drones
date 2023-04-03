
package com.prueba.drones.exception;

import java.util.List;
import org.springframework.http.HttpStatus;

public class InvalidInputLoadDrone extends RuntimeException {
    private List<String> errorMessages;

    public InvalidInputLoadDrone() {
        super();
    }

    public InvalidInputLoadDrone(String message) {
        super(message);
    }

    public InvalidInputLoadDrone(List<String> errorMessages) {
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
