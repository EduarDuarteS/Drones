package com.prueba.drones.enums;

public enum DroneError {
    INVALID_REQUEST("Invalid request"),
    INVALID_DRONE_STATE("Invalid drone state"),
    INVALID_SERIAL_NUMBER("Invalid serial number. It must have a maximum of 100 characters."),
    NEGATIVE_DRONE_WEIGHT("Drone weight cannot be negative"),
    MISSING_DRONE_WEIGHT("Drone weight is required"),
    EXCEEDED_DRONE_WEIGHT_LIMIT("Exceeded drone weight limit"),
    INVALID_BATTERY_CAPACITY("Invalid drone battery capacity. The capacity must be between 0% and 100%."),
    INVALID_MEDICATION_WEIGHT("Invalid medication weight"),
    INVALID_MEDICATION_CODE("Invalid medication code"),
    INVALID_MEDICATION_NAME("Invalid medication name"),
    INVALID_MEDICATION_IMAGE("Invalid medication image"),
    INVALID_DRONE_MODEL("invalid drone Model"),


    DRONE_NOT_FOUND("Drone not found"),
    MEDICATION_NOT_FOUND("Medication not found"),
    DRONE_OVERWEIGHT("Drone overweight"),
    DRONE_BATTERY_LOW("Drone battery low");

    private final String message;

    DroneError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
