/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.dto;

import org.springframework.web.bind.annotation.ModelAttribute;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Description goes here.
 * Created by ckassab on 16/06/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Schema(example = "pancho@plainsboro.org")
    private String email;
    @Schema(example = "Pancho Pistolas")
    private String name;
    @Schema(example = "Neurosurgeon")
    private String specialization;
    @Schema(example = "Neurologist")
    private String degree;
    @Schema(example = "Wisconsin")
    private String state;
    @Schema(example = "Milwaukee")
    private String city;
}
