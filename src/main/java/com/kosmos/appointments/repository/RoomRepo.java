/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.appointments.dto.Doctor;
import com.kosmos.appointments.dto.Room;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
public interface RoomRepo extends JpaRepository<Room, Integer> {
}
