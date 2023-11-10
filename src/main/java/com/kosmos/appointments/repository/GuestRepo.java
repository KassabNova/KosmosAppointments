/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.appointments.dto.Guest;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
public interface GuestRepo extends JpaRepository<Guest, Integer> {
}
