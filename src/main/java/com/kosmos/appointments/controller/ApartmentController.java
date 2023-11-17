/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.appointments.dto.Apartment;
import com.kosmos.appointments.dto.Reservation;
import com.kosmos.appointments.repository.ApartmentRepo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/**
 * Class Description goes here.
 * Created by ckassab on 09/11/23
 */
@RestController
@RequestMapping("/apartments")
@Component
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ApartmentController {

    private ApartmentRepo apartmentRepo;

    /**
     * Create an appointment, validating it before.
     * @param apartment the appointment to create
     * @return The appointment info
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Apartment> createApartment(@Valid @RequestBody Apartment apartment) {
        //appointment = new Appointment(new Date());
        Apartment createdApartment = new Apartment();
        if(!apartment.getRoomNumber().isEmpty()){
            createdApartment = apartmentRepo.save(apartment);
        } else {
            return ResponseEntity.badRequest().body(createdApartment);
        }
        return ResponseEntity.ok(createdApartment);
    }

    /**
     * Get all the reservations for a specific apartment, ordered by its date
     * @param  apartmentId, an apartment ID
     * @return A list of appointments that belong to a specific room
     */
    @GetMapping(value = "/{apartmentId}", produces = "application/json")
    public ResponseEntity<List<Reservation>> getAllAppointmentsForRoom(@PathVariable Integer apartmentId) {

        var apartmentInfo = apartmentRepo.findById(apartmentId);
        if(apartmentInfo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Reservation> reservations = apartmentInfo.get().getReservations();

        return ResponseEntity.ok(reservations);
    }

    /**
     * Return ALL guests
     * @return Return all guests based on login info
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Apartment>> getAllApartments() {
        try {
            return ResponseEntity.ok(apartmentRepo.findAll());
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
