package com.aldebaran.qrcodeapi.controller;

import com.aldebaran.qrcodeapi.entity.QRCode;
import com.aldebaran.qrcodeapi.service.QRCodeService;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name="qrcode source")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @Operation(summary = "Generates a QRCode given an expiration time in hours")
    @PostMapping(path = "/generate/{validUntilInHours}")
    public ResponseEntity<QRCode> generate(@PathVariable long validUntilInHours) throws IOException, WriterException {
        //TODO: validate validUntilInHours to accept only {2,6,12,24}
        QRCode qrcode = qrCodeService.create(validUntilInHours);
        log.info("POST: QRCode Generated with ULID: {}, expiration time in hours: {}, created at: {}, valid until: {}",qrcode.getUlid(), validUntilInHours, qrcode.getCreatedAt(), qrcode.getValidUntil());
        return ResponseEntity.status(HttpStatus.CREATED).body(qrcode);
    }

    @Operation(summary = "Validates a QRCode given a existing ULID")
    @GetMapping(path = "/validate/{ulid}")
    public ResponseEntity<QRCode> validate(@PathVariable String ulid) throws Exception {
        QRCode qrcode = qrCodeService.validate(ulid);
        log.info("GET: QRCode Generated with ULID: {}, created at: {}, read at: {}, valid until: {}", qrcode.getUlid(), qrcode.getCreatedAt(), qrcode.getReadAt(), qrcode.getValidUntil());
        return ResponseEntity.status(HttpStatus.OK).body(qrcode);
    }

}
