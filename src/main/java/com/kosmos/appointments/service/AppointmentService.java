/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kosmos.appointments.dto.Appointment;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@Service
public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>(){
        {
            add(new Appointment());
            add(new Appointment());
            add(new Appointment());
        }
    };
    public List<Appointment> getAll() {
        return appointments;
    }

    public int addPlant(Appointment plant) {

        if (appointments.contains(plant)) {
            System.out.println("found a duplicate");
            return -1;
        }
        appointments.add(plant);
        return 0;
    }
}
