/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "users")
@Builder
@ToString(exclude = "apartments")
@EqualsAndHashCode(exclude = "apartments")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(example = "b85a7588-1094-4311-835a-0b460d2f64cf")
    @NotNull
    private UUID id;
    @Schema(example = "Pancho")
    private String firstName;
    @Schema(example = "Pistolas")
    private String lastName;
    @Schema(example = "true")
    private Boolean isAdmin;
    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Apartment> apartments;

}
