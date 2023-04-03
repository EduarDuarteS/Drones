package com.prueba.drones.validator;

import com.prueba.drones.controller.dto.medicineLoadDTOs.MedicationDTO;
import com.prueba.drones.enums.DroneError;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;


@Component
public class MedicationValidators  {

    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z0-9-_]+");
    private static final Pattern CODE_PATTERN = Pattern.compile("[A-Z0-9_]+");
    private static final Pattern MEDICATION_CODE_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");




    public static void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(errors, "weight", "weight.empty");
        ValidationUtils.rejectIfEmpty(errors, "code", "code.empty");

        MedicationDTO medication = (MedicationDTO) target;
        List<String> validationErrors = MedicationValidators.validate(medication);
        for (String error : validationErrors) {
            errors.rejectValue("code", error);
        }
    }

    public static List<String> validate(MedicationDTO medication) {
        List<String> errors = new ArrayList<>();

        if (medication.getName() == null || medication.getName().isEmpty()) {
            errors.add(DroneError.MISSING_MEDICATION_NAME.getMessage());
        } else if (!NAME_PATTERN.matcher(medication.getName()).matches()) {
            errors.add(DroneError.INVALID_MEDICATION_NAME.getMessage());
        }

        if (medication.getCode() == null || medication.getCode().isEmpty()) {
            errors.add(DroneError.MISSING_MEDICATION_CODE.getMessage());
        } else if (!CODE_PATTERN.matcher(medication.getCode()).matches()) {
            errors.add(DroneError.INVALID_MEDICATION_CODE.getMessage());
        } else if (!MEDICATION_CODE_PATTERN.matcher(medication.getCode()).matches()) {
            errors.add(DroneError.INVALID_MEDICATION_CODE.getMessage());
        }

        if (medication.getWeight() == null) {
            errors.add(DroneError.MISSING_MEDICATION_WEIGHT.getMessage());
        } else if (medication.getWeight() <= 0) {
            errors.add(DroneError.INVALID_MEDICATION_WEIGHT.getMessage());
        }

        return errors;
    }
}
