/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.appointments.dto.Apartment;
import com.kosmos.appointments.dto.User;
import com.kosmos.appointments.logic.AppointmentLogic;
import com.kosmos.appointments.dto.Reservation;
import com.kosmos.appointments.repository.ApartmentRepo;
import com.kosmos.appointments.repository.ReservationRepo;
import com.kosmos.appointments.repository.UserRepo;

import lombok.AllArgsConstructor;

/**
 * Class Description goes here.
 * Created by ckassab on 09/11/23
 */
@RestController
@RequestMapping("/users")
@Component
@AllArgsConstructor
public class UserController {

    private UserRepo userRepo;

    /**
     * Create an appointment, validating it before.
     * @param user the appointment to create
     * @return The appointment info
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<User> createAppointment(@RequestBody User user) {
        //appointment = new Appointment(new Date());
        User createdUser = new User();
        if(true){
            createdUser = userRepo.save(createdUser);
        } else {
            return ResponseEntity.badRequest().body(createdUser);
        }
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Return ALL users
     * @return Return all Users based on login info
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userRepo.findAll());
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
