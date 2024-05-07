package com.aldebaran.qrcodeapi.repository;

import com.aldebaran.qrcodeapi.entity.QRCode;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface QRCodeRepository extends JpaRepository<QRCode, String> {
}
