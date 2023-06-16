/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kosmos.appointments.dto.Appointment;

import com.kosmos.appointments.dto.Doctor;
import com.kosmos.appointments.dto.Room;
import com.kosmos.appointments.repository.AppointmentRepo;
import com.kosmos.appointments.repository.DoctorRepo;
import com.kosmos.appointments.repository.RoomRepo;

import jakarta.validation.Valid;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private RoomRepo roomRepo;

    /**
     * Create an appointment, validating it before.
     * @param appointment the appointment to create
     * @return The appointment info
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        //appointment = new Appointment(new Date());
        //var test = new ResponseEntity<Appointment>(service.getAll(), HttpStatus.CREATED)
        var test = appointmentRepo.save(appointment);
        return ResponseEntity.ok(test);
    }

    /**
     * Get an appointment by id
     * @param id the appointment to search
     * @return Search for an appointment by it's ID
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public Appointment getAppointmentById(UUID id) {

        return appointmentRepo.findById(id).get();
    }

    /**
     * Return ALL appointments
     * @return Return all appointments
     */
    @GetMapping(value = "/", produces = "application/json")
    public List<Appointment> getAllAppointments() {

        return appointmentRepo.findAll();
    }

    /**
     * Get all the appointments that a doctor have, ordered by it's date
     * @param  doctor, but only it's ID is required
     * @return A list of appointments that belong to a specific doctor
     */
    @GetMapping(value = "/doctor/{doctorId}", produces = "application/json")
    public List<Appointment> getAllAppointmentsForDoctor(Doctor doctor) {

        var doctorInfo = doctorRepo.findById(doctor.getId()).get();
        List<Appointment> appointments = doctorInfo.getDoctorAppointments();
        return appointments;
    }

    /**
     * Just a dummy method to init quickly
     */
    @GetMapping(value = "/init", produces = "application/json")
    public void init() {

        var doctors = List.of(
                Doctor.builder()
                        .firstName("Carlos")
                        .lastName("Kassab")
                        .specialization("Neurology")
                        .build(),
                Doctor.builder()
                        .firstName("Joseph")
                        .lastName("Sasson")
                        .specialization("Cardiology")
                        .build(),
                Doctor.builder()
                        .firstName("Pepe")
                        .lastName("Grillo")
                        .specialization("Traumatology")
                        .build()
                );
        var rooms = List.of(
                Room.builder()
                        .roomNumber("1")
                        .floor("PB")
                        .build(),
                Room.builder()
                        .roomNumber("2")
                        .floor("PB")
                        .build(),
                Room.builder()
                        .roomNumber("1")
                        .floor("1")
                        .build(),
                Room.builder()
                        .roomNumber("2")
                        .floor("1")
                        .build());

        doctorRepo.saveAll(doctors);
        roomRepo.saveAll(rooms);
    }
}
