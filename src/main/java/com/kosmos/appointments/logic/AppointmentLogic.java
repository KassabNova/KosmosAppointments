/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.logic;

import org.springframework.stereotype.Service;

import com.kosmos.appointments.dto.Reservation;
import com.kosmos.appointments.repository.ReservationRepo;
import com.kosmos.appointments.repository.UserRepo;
import com.kosmos.appointments.repository.ApartmentRepo;

import lombok.AllArgsConstructor;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@AllArgsConstructor
@Service
public class AppointmentLogic {

    private ReservationRepo reservationRepo;
    private UserRepo userRepo;
    private ApartmentRepo apartmentRepo;

    public boolean isAppointmentValid(Reservation reservation) {

        //There must be 4 validations
        var isAppointmentValid = isRoomAvailable(reservation) &&
                isDoctorAvailable(reservation) &&
                isPatientAvailable(reservation);
        //Validate there is no appointment at the same room at the same time
        //Validate the doctor has no other appointment at the requested time and does not have 8 or more appointments
        //Validate the patient has no other appointment two hours before or after this one


        return isAppointmentValid;
    }

    private boolean isRoomAvailable(Reservation requestedReservation){

        var roomInfo = apartmentRepo.findById(requestedReservation.apartment.getId());
        if(roomInfo.isEmpty()){
            return true;
        }
        var roomAppointments = roomInfo.get().getReservations();

        var isRoomAvailable = roomAppointments.stream()
                .filter(appointment -> appointment == requestedReservation)
                .findFirst().isEmpty();

        return isRoomAvailable;
    }

    private boolean isDoctorAvailable(Reservation reservation){

        return true;
    }

    private boolean isPatientAvailable(Reservation reservation){

        return true;
    }
}
