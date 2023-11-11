/*
 * Copyright (c) 2023 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kosmos.appointments.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Class Description goes here.
 * Created by ckassab on 11/11/23
 */
@RestController
@RequestMapping("/barcodes")
public class BarcodeController {

    @GetMapping(value = "/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode) {
        try{
            var generatedQR = generateQR(barcode);
            return ResponseEntity.ok(generatedQR);
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    public BufferedImage generateQR(String barcode) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcode, BarcodeFormat.QR_CODE, 600, 600);

        saveQR(bitMatrix, barcode);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public void saveQR(BitMatrix matrix, String barcode) throws IOException {
        MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(Paths.get("").toAbsolutePath().getParent().toString() + "/reservationQR/" + barcode));
    }
}
