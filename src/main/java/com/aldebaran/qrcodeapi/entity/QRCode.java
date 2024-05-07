package com.aldebaran.qrcodeapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.aldebaran.qrcodeapi.qrcodeutils.DateTimeCaster.milisecodsToLocalDateTime;

@Data
@Entity
public class QRCode {

    @Id
    private String ulid = null;
    @Lob
    private String code = null;
    @Getter(AccessLevel.NONE)
    private Long createdAt = System.currentTimeMillis();
    @Getter(AccessLevel.NONE)
    private Long readAt = null;
    @Getter(AccessLevel.NONE)
    private Long validUntil = null;
    private Boolean active = true;

    public LocalDateTime getCreatedAt(){
        return milisecodsToLocalDateTime(createdAt);
    }
    public LocalDateTime getReadAt(){
        return milisecodsToLocalDateTime(readAt);
    }
    public LocalDateTime getValidUntil(){
        return milisecodsToLocalDateTime(validUntil);
    }

}
