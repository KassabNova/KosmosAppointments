/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.appointments.dto.Guest;
import com.kosmos.appointments.logic.GuestLogic;
import com.kosmos.appointments.repository.GuestRepo;

import lombok.AllArgsConstructor;

/**
 * Class Description goes here.
 * Created by ckassab on 09/11/23
 */
@RestController
@RequestMapping("/guests")
@Component
@AllArgsConstructor
public class GuestController {

    private GuestRepo guestRepo;
    private GuestLogic guestLogic = new GuestLogic(guestRepo);

    /**
     * Create a guest registration for a reservation, validating it before inserting it.
     * @param guest the guest to associate to a reservation
     * @return The appointment info
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest createdGuest = new Guest();
        if(guestLogic.isGuessRegistrationValid(guest)){
            createdGuest = guestRepo.save(guest);

        } else {
            return ResponseEntity.badRequest().body(createdGuest);
        }
        return ResponseEntity.ok(createdGuest);
    }

    /**
     * Return ALL guests
     * @return Return all guests based on login info
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Guest>> getAllAppointments() {

        try {
            return ResponseEntity.ok(guestRepo.findAll());
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    /**
     * Create a guest registration for a reservation, validating it before inserting it.
     * @param guests the guests to associate to a reservation
     * @return The appointment info
     */
    @PostMapping(value = "/batch", produces = "application/json")
    public ResponseEntity<List<Guest>> createGuests(@RequestBody List<Guest> guests) {
        //var createdGuest = List.of(guests);
        return ResponseEntity.ok(guests);

    }

}
