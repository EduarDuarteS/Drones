package com.prueba.drones;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.prueba.drones.model.Drone;
import com.prueba.drones.enums.DroneState;
import com.prueba.drones.model.Medication;
import com.prueba.drones.service.DroneService;
import com.prueba.drones.model.DroneMedication;
import com.prueba.drones.repository.DroneRepository;
import com.prueba.drones.repository.DroneMedicationRepository;
import com.prueba.drones.controller.dto.medicineLoadDTOs.MedicationDTO;
import com.prueba.drones.controller.dto.dronRequestDTOs.DroneRequestDto;

import org.apache.commons.codec.binary.Base64;

@SpringBootTest
@DisplayName("Load Drone Service Test")
public class DroneControllerTest_loadMedication {

    private static final String MEDICATION_IMAGES_PATH = "./src/test/java/com/prueba/drones/resources/";
    private static final String MEDICATION_IMAGE_EXTENSION = ".png";

    private MedicationDTO medication1;
    private MedicationDTO medication2;
    private MedicationDTO medication3;

    @Autowired
    private DroneService droneService;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMedicationRepository droneMedicationRepository;

    private static Drone drone1;
    private Drone drone2;

    private byte[] img1Bytes;
    private byte[] img2Bytes;
    private byte[] img3Bytes;

    // Data Drone 1
    final private String serialD1L = "DroneTest1";
    final private String modelD1 = "Heavyweight";
    final private int weightL1 = 500;
    final private int bateryC1 = 100;
    final private DroneState droneState1 = DroneState.IDLE;

    // Data Dron 2
    private String serialD2L = "DroneTest2";
    private String modelD2 = "Lightweight";
    private int weightL2 = 300;
    private int bateryC2 = 100;
    private DroneState droneState2 = DroneState.IDLE;

    // Data Medicines
    private String mediName1 = "acetaminofen";
    private Double medWeigth1 = 100.0;
    private String medCode1 = "medi_1";

    private String mediName2 = "Buscapina";
    private Double medWeigth2 = 50.0;
    private String medCode2 = "medi_2";

    private String mediName3 = "clonazepam";
    private Double medWeigth3 = 150.0;
    private String medCode3 = "medi_3";

    @BeforeEach
    public void setUp() throws IOException {

        // Drone Ser1
        drone1 = new Drone();
        drone1.setSerialNumber(serialD1L);
        drone1.setModel(modelD1);
        drone1.setWeightLimit(weightL1);
        drone1.setBatteryCapacity(bateryC1);
        drone1.setState(droneState1);
        droneService.registerDrone(new DroneRequestDto(drone1));

        // Drone Ser2
        drone2 = new Drone();
        drone2.setSerialNumber(serialD2L);
        drone2.setModel(modelD2);
        drone2.setWeightLimit(weightL2);
        drone2.setBatteryCapacity(bateryC2);
        drone2.setState(droneState2);
        droneService.registerDrone(new DroneRequestDto(drone2));

        // Medications 1, 2 and 3
        medication1 = new MedicationDTO();
        medication1.setName(mediName1);
        medication1.setWeight(medWeigth1);
        medication1.setCode(medCode1);
        medication1.setImage(getImageAsBase64(mediName1));

        medication2 = new MedicationDTO();
        medication2.setName(mediName2);
        medication2.setWeight(medWeigth2);
        medication2.setCode(medCode2);
        medication2.setImage(getImageAsBase64(mediName2));

        medication3 = new MedicationDTO();
        medication3.setName(mediName3);
        medication3.setWeight(medWeigth3);
        medication3.setCode(medCode3);
        medication3.setImage(getImageAsBase64(mediName3));

        // images
        img2Bytes = Files.readAllBytes(Paths.get(MEDICATION_IMAGES_PATH + mediName2 + MEDICATION_IMAGE_EXTENSION));
        img3Bytes = Files.readAllBytes(Paths.get(MEDICATION_IMAGES_PATH + mediName3 + MEDICATION_IMAGE_EXTENSION));
        img1Bytes = Files.readAllBytes(Paths.get(MEDICATION_IMAGES_PATH + mediName1 + MEDICATION_IMAGE_EXTENSION));

    }

    @AfterEach
    public void tearDown() {
        droneRepository.deleteById(serialD1L);
        droneRepository.deleteById(serialD2L);
    }

    @Test
    public void shouldLoadMedicationsSuccessfullyWhenDroneIsAvailable() throws IOException {

        // Given
        List<MedicationDTO> medicationDTOs = Arrays.asList(medication1, medication2);
        droneService.loadMedicines(serialD1L, medicationDTOs);

        // When
        DroneMedication droneMedication = droneMedicationRepository.findByDroneSerialNumberWithMedications(serialD1L);
        // Then
        Medication m1Save = droneMedication.getMedications().get(0);
        assertEquals(m1Save.getName(), mediName1);
        assertEquals(m1Save.getWeight(), medWeigth1);
        assertEquals(m1Save.getCode(), medCode1);
        assertArrayEquals(m1Save.getImage(), img1Bytes);

        Medication m2Save = droneMedication.getMedications().get(1);
        assertEquals(m2Save.getName(), mediName2);
        assertEquals(m2Save.getWeight(), medWeigth2);
        assertEquals(m2Save.getCode(), medCode2);
        assertArrayEquals(m2Save.getImage(), img2Bytes);
    }

    @Test
    public void testLoad3MedicationsToDrone() throws IOException {

        // Given
        List<MedicationDTO> medications = new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);
        medications.add(medication3);
        droneService.loadMedicines(serialD2L, medications);

        // When
        DroneMedication droneMedication = droneMedicationRepository.findByDroneSerialNumberWithMedications(serialD2L);
        // Then
        Medication m1Save = droneMedication.getMedications().get(0);
        assertEquals(m1Save.getName(), mediName1);
        assertEquals(m1Save.getWeight(), medWeigth1);
        assertEquals(m1Save.getCode(), medCode1);
        assertArrayEquals(m1Save.getImage(), img1Bytes);

        Medication m2Save = droneMedication.getMedications().get(1);
        assertEquals(m2Save.getName(), mediName2);
        assertEquals(m2Save.getWeight(), medWeigth2);
        assertEquals(m2Save.getCode(), medCode2);
        assertArrayEquals(m2Save.getImage(), img2Bytes);

        Medication m3Save = droneMedication.getMedications().get(2);
        assertEquals(m3Save.getName(), mediName3);
        assertEquals(m3Save.getWeight(), medWeigth3);
        assertEquals(m3Save.getCode(), medCode3);
        assertArrayEquals(m3Save.getImage(), img3Bytes);

    }

    public static String getImageAsBase64(String imgName) throws IOException {
        String imagePath = MEDICATION_IMAGES_PATH + imgName + MEDICATION_IMAGE_EXTENSION;
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        return Base64.encodeBase64String(fileContent);
    }

}