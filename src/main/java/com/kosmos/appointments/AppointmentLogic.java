/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kosmos.appointments.dto.Appointment;
import com.kosmos.appointments.repository.AppointmentRepo;
import com.kosmos.appointments.repository.DoctorRepo;
import com.kosmos.appointments.repository.RoomRepo;

import lombok.AllArgsConstructor;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@AllArgsConstructor
@Service
public class AppointmentLogic {

    private AppointmentRepo appointmentRepo;
    private DoctorRepo doctorRepo;
    private RoomRepo roomRepo;

    public boolean isAppointmentValid(Appointment appointment) {

        //There must be 4 validations
        var isAppointmentValid = false;
        //Validate there is no appointment at the same room at the same time
        //Validate the doctor has no other appointment at the requested time and does not have 8 or more appointments
        //Validate the patient has no other appointment two hours before or after this one

        if(isRoomAvailable(appointment) &&
                isDoctorAvailable(appointment) &&
                isPatientAvailable(appointment)){
            isAppointmentValid = true;
        }


        return isAppointmentValid;
    }

    private boolean isRoomAvailable(Appointment requestedAppointment){

        var roomInfo = roomRepo.findById(requestedAppointment.consultingRoom.getId());
        if(roomInfo.isEmpty()){
            return true;
        }
        var roomAppointments = roomInfo.get().getRoomAppointments();

        var isRoomAvailable = roomAppointments.stream()
                .filter(appointment -> appointment == requestedAppointment)
                .findFirst().isEmpty();

        return isRoomAvailable;
    }

    private boolean isDoctorAvailable(Appointment appointment){

        return true;
    }

    private boolean isPatientAvailable(Appointment appointment){

        return true;
    }
}
