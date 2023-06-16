/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import java.util.Date;

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

import com.kosmos.appointments.service.AppointmentService;

import jakarta.validation.Valid;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        //appointment = new Appointment(new Date());
        //var test = new ResponseEntity<Appointment>(service.getAll(), HttpStatus.CREATED)
        return ResponseEntity.ok(appointment);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Appointment getAppointmentById(Integer id) {

        var appointment = new Appointment();
        appointment.date = new Date();
        return appointment;
    }
}
