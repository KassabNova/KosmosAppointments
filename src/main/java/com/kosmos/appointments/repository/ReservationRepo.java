/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.appointments.dto.Reservation;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
public interface ReservationRepo extends JpaRepository<Reservation, UUID> {

}
