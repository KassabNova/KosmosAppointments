/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
@Builder
@ToString(exclude = "doctorAppointments")
@EqualsAndHashCode(exclude = "doctorAppointments")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(example = "0266e69d-329d-4f51-a935-8fb7cc1139ee")
    @NotNull
    private UUID id;
    @Schema(example = "Pancho")
    private String firstName;
    @Schema(example = "Pistolas")
    private String lastName;
    @Schema(example = "Neurologist")
    private String specialization;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> doctorAppointments;
}
