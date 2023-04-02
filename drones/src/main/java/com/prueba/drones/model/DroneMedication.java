package com.prueba.drones.model;

import jakarta.persistence.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "drone_medication")
public class DroneMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", referencedColumnName = "serial_number")
    private Drone drone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "drone_medication_medication", joinColumns = {
            @JoinColumn(name = "drone_medication_id") }, inverseJoinColumns = { @JoinColumn(name = "medication_id") })
    private List<Medication> medications;

    @Column(name = "quantity")
    private Integer quantity;

    public DroneMedication() {
    }

    public DroneMedication(Drone drone, List<Medication> medications, Integer quantity) {
        this.drone = drone;
        this.medications = medications;
        this.quantity = quantity;
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
