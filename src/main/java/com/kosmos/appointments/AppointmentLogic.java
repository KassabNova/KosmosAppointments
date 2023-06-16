/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments;

import com.kosmos.appointments.dto.Appointment;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
public class AppointmentLogic {

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

    private boolean isRoomAvailable(Appointment appointment){

        return true;
    }

    private boolean isDoctorAvailable(Appointment appointment){

        return true;
    }

    private boolean isPatientAvailable(Appointment appointment){

        return true;
    }
}
