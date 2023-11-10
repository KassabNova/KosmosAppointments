/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.appointments.AppointmentLogic;
import com.kosmos.appointments.dto.Reservation;

import com.kosmos.appointments.dto.User;
import com.kosmos.appointments.dto.Apartment;
import com.kosmos.appointments.repository.ReservationRepo;
import com.kosmos.appointments.repository.UserRepo;
import com.kosmos.appointments.repository.ApartmentRepo;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@RestController
@RequestMapping("/reservations")
@Component
public class ReservationController {

    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ApartmentRepo apartmentRepo;
    private AppointmentLogic appointmentLogic = new AppointmentLogic(reservationRepo, userRepo, apartmentRepo);

    /**
     * Create an appointment, validating it before.
     * @param reservation the appointment to create
     * @return The appointment info
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Reservation> createAppointment(@RequestBody Reservation reservation) {
        //appointment = new Appointment(new Date());
        Reservation createdReservation = new Reservation();
        if(appointmentLogic.isAppointmentValid(reservation)){
            createdReservation = reservationRepo.save(reservation);

        } else {
            return ResponseEntity.badRequest().body(createdReservation);
        }
        return ResponseEntity.ok(createdReservation);
    }

    /**
     * Get an appointment by id
     * @param id the appointment to search
     * @return Search for an appointment by its ID
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Reservation> getAppointmentById(UUID id) {
        var appointment = reservationRepo.findById(id);
        if(appointment.isPresent()){
            return ResponseEntity.ok(appointment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Return ALL appointments
     * @return Return all appointments
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Reservation>> getAllAppointments() {

        try {
            return ResponseEntity.ok(reservationRepo.findAll());

        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    /**
     * Get all the appointments that a doctor have, ordered by its date
     * @param  user, but only it's ID is required
     * @return A list of appointments that belong to a specific doctor
     */
    @GetMapping(value = "/doctor/{doctorId}", produces = "application/json")
    public ResponseEntity<List<Reservation>> getAllAppointmentsForDoctor(User user) {

        var userInfo = userRepo.findById(user.getId());
        if(userInfo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Reservation> reservations = userInfo.get()
                .getApartments()
                .stream()
                .map(Apartment::getReservations)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservations);
    }

    /**
     * Get all the appointments that a room has, ordered by its date
     * @param  apartment, but only it's ID is required
     * @return A list of appointments that belong to a specific room
     */
    @GetMapping(value = "/room/{roomId}", produces = "application/json")
    public ResponseEntity<List<Reservation>> getAllAppointmentsForRoom(Apartment apartment) {

        var roomInfo = apartmentRepo.findById(apartment.getId());
        if(roomInfo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Reservation> reservations = roomInfo.get().getReservations();

        return ResponseEntity.ok(reservations);
    }

    /**
     * Get all the appointments that a doctor have, ordered by its date
     * @param  date, but only it's yyyy-mm-dd is used.
     * @return A list of appointments that belong to a specific day
     */
    @GetMapping(value = "/availability/", produces = "application/json")
    public ResponseEntity<List<Reservation>> getAllAppointmentsForDay(@RequestParam("day") Date date) {

        //TODO Write the custom query to search by day
        List<Reservation> reservations = List.of();
        if(reservations.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservations);
    }

    /**
     * Just a dummy method to init quickly
     */
    @GetMapping(value = "/init", produces = "application/json")
    public void init() {

        var doctors = List.of(
                User.builder()
                        .firstName("Carlos")
                        .lastName("Kassab")
                        .build(),
                User.builder()
                        .firstName("Joseph")
                        .lastName("Sasson")
                        .build(),
                User.builder()
                        .firstName("Pepe")
                        .lastName("Grillo")
                        .build()
                );
        var rooms = List.of(
                Apartment.builder()
                        .roomNumber("1")
                        .floor("PB")
                        .build(),
                Apartment.builder()
                        .roomNumber("2")
                        .floor("PB")
                        .build(),
                Apartment.builder()
                        .roomNumber("1")
                        .floor("1")
                        .build(),
                Apartment.builder()
                        .roomNumber("2")
                        .floor("1")
                        .build());

        userRepo.saveAll(doctors);
        apartmentRepo.saveAll(rooms);
    }
}
