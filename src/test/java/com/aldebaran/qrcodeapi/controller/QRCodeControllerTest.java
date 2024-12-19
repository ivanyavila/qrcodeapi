package com.aldebaran.qrcodeapi.controller;

import com.aldebaran.qrcodeapi.entity.QRCode;
import com.aldebaran.qrcodeapi.service.QRCodeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(QRCodeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class QRCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QRCodeService qrCodeService;

    private QRCode qrCode;

    @BeforeEach
    public void setUp() {
        qrCode = new QRCode();

    }

    @Test
    public void QRCodeController_GenerateQRCode_ReturnCreated() throws Exception {
        given(qrCodeService.create(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
        when(qrCodeService.create(12)).thenReturn(qrCode);
        ResultActions response = mockMvc
                .perform(post("/generate/{validUntilInHours}"));
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ulid", CoreMatchers.is(qrCode.getUlid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(qrCode.getCode())));
                //.andExpect(MockMvcResultMatchers.jsonPath("$."))
    }

}
