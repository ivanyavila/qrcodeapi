package com.aldebaran.qrcodeapi.qrcodeutils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Component
public class DateTimeCaster {

    public static LocalDateTime milisecodsToLocalDateTime(Long milliseconds) {
        if (Objects.isNull(milliseconds))
            return null;

        return Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Long localDateTimeLoMilliseconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
