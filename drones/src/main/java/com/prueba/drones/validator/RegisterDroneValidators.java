package com.prueba.drones.validator;

import java.util.List;

import com.prueba.drones.controller.dto.DroneRequestDto;
import com.prueba.drones.enums.DroneError;
import com.prueba.drones.enums.DroneModel;

import java.util.ArrayList;


public class RegisterDroneValidators {

    public static boolean isValidDroneModel(String model) {
        for (DroneModel allowedModel : DroneModel.values()) {
            if (allowedModel.name().equalsIgnoreCase(model)) {
                return true;
            }
        }
        return false;
    }

    public static void validateBatteryCapacity(double batteryCapacity, List<String> errors) {
        if (batteryCapacity < 0 || batteryCapacity > 100) {
            errors.add(DroneError.INVALID_BATTERY_CAPACITY.getMessage());
        }
    }

    public static void validateWeightLimit(double weightLimit, List<String> errors) {
        if (weightLimit > 500) {
            errors.add(DroneError.EXCEEDED_DRONE_WEIGHT_LIMIT.getMessage());
        }
        if (weightLimit < 0) {
            errors.add(DroneError.NEGATIVE_DRONE_WEIGHT.getMessage());
        }
        if (weightLimit == 0) {
            errors.add(DroneError.MISSING_DRONE_WEIGHT.getMessage());
        }
    }

    public static void validateSerialNumber(String serial, List<String> errors) {
        if (serial.length() > 100) {
            errors.add(DroneError.INVALID_SERIAL_NUMBER.getMessage());
        }
    }
    
    public static List<String> validate(DroneRequestDto droneRequestDto) {
        List<String> errors = new ArrayList<>();

        validateSerialNumber(droneRequestDto.getSerialNumber(), errors);
        validateWeightLimit(droneRequestDto.getWeightLimit(), errors);
        validateBatteryCapacity(droneRequestDto.getBatteryCapacity(), errors);

        if (!isValidDroneModel(droneRequestDto.getModel())) {
            errors.add(DroneError.INVALID_DRONE_MODEL.getMessage());
        }

        return errors;
    }
}
