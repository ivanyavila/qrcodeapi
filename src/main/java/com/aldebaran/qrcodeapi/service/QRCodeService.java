package com.aldebaran.qrcodeapi.service;

import com.aldebaran.qrcodeapi.entity.QRCode;
import com.aldebaran.qrcodeapi.qrcodeutils.QRCodeGenerator;
import com.aldebaran.qrcodeapi.repository.QRCodeRepository;
import com.google.zxing.WriterException;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.aldebaran.qrcodeapi.qrcodeutils.DateTimeCaster.localDateTimeLoMilliseconds;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class QRCodeService {

    private ULID ulid;
    private final QRCodeGenerator qrCodeGenerator;
    private final QRCodeRepository repository;

    public QRCode create(int validUntilInHours) throws IOException, WriterException {
        QRCode qrCode = new QRCode();
        long createAt = localDateTimeLoMilliseconds(qrCode.getCreatedAt());
        String ulidId = ulid.nextULID(createAt);
        String code = Base64.getEncoder().encodeToString(qrCodeGenerator.getQRCodeBytes(ulidId));
        qrCode.setUlid(ulidId);
        qrCode.setCode(code);
        qrCode.setValidUntil(createAt + (3600000L * validUntilInHours));
        return this.repository.save(qrCode);
    }

    public QRCode validate(String ulidId) throws Exception {
        QRCode qrcode = repository.findById(ulidId)
                .orElseThrow(() -> new NoSuchElementException("QRCode not found with ulid: " + ulidId)); //TODO: Custom exception

        long validUntil = localDateTimeLoMilliseconds(qrcode.getValidUntil());

        if (validUntil < System.currentTimeMillis())
            throw new Exception("QR code already expired: " + qrcode); // TODO: Custom Exception

        if (!qrcode.getActive() || !Objects.isNull(qrcode.getReadAt()))
            throw new Exception("QR code already used: " + qrcode); // TODO: Custom Exception

        if (qrcode.getActive() & Objects.isNull(qrcode.getReadAt())) {
            qrcode.setReadAt(System.currentTimeMillis());
            qrcode.setActive(false);
        }

        return qrcode;
    }
}
