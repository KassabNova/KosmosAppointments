/*
 * Copyright (c) 2023 CKassab, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
@Table(name = "reservations")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APARTMENT_ID")
    public Apartment apartment;
    @NotNull
    public Date startDate;
    @NotNull
    public Date endDate;
    @NotNull
    public Date aproxArrivalDate;
    @NotNull
    public Date checkIn;
    @NotNull
    public Date checkOut;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    public List<Guest> guests;


}
