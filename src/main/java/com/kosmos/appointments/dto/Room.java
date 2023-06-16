/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "rooms")
@Builder
@ToString(exclude = "roomAppointments")
@EqualsAndHashCode(exclude = "roomAppointments")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Schema(example = "1")
    private Integer id;
    @Schema(example = "3")
    private String roomNumber;
    @Schema(example = "PB")
    private String floor;
    @OneToMany(mappedBy = "consultingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> roomAppointments;
}
