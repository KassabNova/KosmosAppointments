/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.appointments.dto.Appointment;
import com.kosmos.appointments.dto.Doctor;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {

}
