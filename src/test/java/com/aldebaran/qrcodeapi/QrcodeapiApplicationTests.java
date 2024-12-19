package com.aldebaran.qrcodeapi;

import com.aldebaran.qrcodeapi.controller.QRCodeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class QrcodeapiApplicationTests {

	@Autowired
	QRCodeAPIApplication qrCodeAPIApplication;

	@Test
	void contextLoads() {
		assertNotNull(qrCodeAPIApplication);
	}

}
