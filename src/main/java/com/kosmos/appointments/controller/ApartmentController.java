/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/apartments")
@Component
@AllArgsConstructor
public class ApartmentController {

    private ReservationRepo reservationRepo;
    private UserRepo userRepo;
    private ApartmentRepo apartmentRepo;
    private AppointmentLogic appointmentLogic = new AppointmentLogic(reservationRepo, userRepo, apartmentRepo);

    /**
     * Create an appointment, validating it before.
     * @param reservation the appointment to create
     * @return The appointment info
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Reservation> createAppointment(@RequestBody Reservation reservation) {
        //appointment = new Appointment(new Date());
        Reservation createdReservation = new Reservation();
        if(appointmentLogic.isAppointmentValid(reservation)){
            createdReservation = reservationRepo.save(reservation);

        } else {
            return ResponseEntity.badRequest().body(createdReservation);
        }
        return ResponseEntity.ok(createdReservation);
    }
}
