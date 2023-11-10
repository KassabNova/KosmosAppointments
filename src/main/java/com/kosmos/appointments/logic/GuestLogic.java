/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.logic;

import org.springframework.stereotype.Service;

import com.kosmos.appointments.dto.Guest;
import com.kosmos.appointments.dto.Reservation;
import com.kosmos.appointments.repository.GuestRepo;

import lombok.AllArgsConstructor;

/**
 * Class Description goes here.
 * Created by ckassab on 09/11/23
 */
@AllArgsConstructor
@Service
public class GuestLogic {

    private GuestRepo guestRepo;
    public boolean isGuessRegistrationValid(Guest guess) {

        //There must be 4 validations
//        var isGuestValid = isRoomAvailable(reservation) &&
//                isDoctorAvailable(reservation) &&
//                isPatientAvailable(reservation);
        //Validate there is no appointment at the same room at the same time
        //Validate the doctor has no other appointment at the requested time and does not have 8 or more appointments
        //Validate the patient has no other appointment two hours before or after this one


        return true;
    }
}
