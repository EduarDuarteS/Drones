package com.prueba.drones.enums;

public enum DroneError {
    INVALID_REQUEST("Invalid request"),
    INVALID_DRONE_STATE("Invalid drone state"),
    INVALID_DRONE_MODEL("invalid drone Model"),
    MISSING_DRONE_WEIGHT("Drone weight is required"),
    INVALID_MEDICATION_CODE("Invalid medication code"),
    INVALID_MEDICATION_NAME("Invalid medication name"),
    INVALID_MEDICATION_IMAGE("Invalid medication image"),
    INVALID_MEDICATION_WEIGHT("Invalid medication weight"),
    NEGATIVE_DRONE_WEIGHT("Drone weight cannot be negative"),
    EXCEEDED_DRONE_WEIGHT_LIMIT("Exceeded drone weight limit"),
    INVALID_SERIAL_NUMBER("Invalid serial number. It must have a maximum of 100 characters."),
    INVALID_BATTERY_CAPACITY("Invalid drone battery capacity. The capacity must be between 0% and 100%."),


    DRONE_NOT_FOUND("Drone not found"),
    DRONE_OVERWEIGHT("Drone overweight"),
    DRONE_BATTERY_LOW("Drone battery low"),
    MEDICATION_NOT_FOUND("Medication not found"),
    MISSING_MEDICATION_CODE("Missing medication code"),
    MISSING_MEDICATION_NAME("Missing medication name"),
    MISSING_MEDICATION_WEIGHT("Missing medication weigt"),
    INVALID_WEIGHT("Invalid weight. Weight must be greater than 0."),
    INVALID_NAME("Invalid name. Only letters, numbers, '-' and '_' are allowed."),
    INVALID_IMAGE("Invalid image. A picture of the medication case is required."),
    INVALID_CODE("Invalid code. Only upper case letters, underscore and numbers are allowed.");


    private final String message;

    DroneError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
