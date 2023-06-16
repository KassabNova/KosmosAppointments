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
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        //appointment = new Appointment(new Date());
        //var test = new ResponseEntity<Appointment>(service.getAll(), HttpStatus.CREATED)
        var test = appointmentRepo.save(appointment);
        return ResponseEntity.ok(test);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Appointment getAppointmentById(UUID id) {

        return appointmentRepo.findById(id).get();
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Appointment> getAllAppointments() {

        return appointmentRepo.findAll();
    }

    @GetMapping(value = "/doctor/{doctorId}", produces = "application/json")
    public List<Appointment> getAllAppointmentsForDoctor(Doctor doctor) {

        var doctorInfo = doctorRepo.findById(doctor.getId()).get();
        List<Appointment> appointments = doctorInfo.getDoctorAppointments();
        return appointments;
    }

    @GetMapping(value = "/init", produces = "application/json")
    public void init() {
        var doctor = Doctor.builder()
                .firstName("Carlos")
                .lastName("Kassab")
                .specialization("Neurology")
                .build();
        var room = Room.builder()
                .roomNumber("1")
                .floor("PB")
                .build();
        doctorRepo.save(doctor);
        roomRepo.save(room);
    }
}
