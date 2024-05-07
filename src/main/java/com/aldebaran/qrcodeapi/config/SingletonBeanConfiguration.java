package com.aldebaran.qrcodeapi.config;

import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.qrcode.QRCodeWriter;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayOutputStream;

@Configuration
public class SingletonBeanConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ULID ulid() {
        return new ULID();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public QRCodeWriter qrCodeWriter() {
        return new QRCodeWriter();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MatrixToImageConfig matrixToImageConfig() {
        return new MatrixToImageConfig( 0xFF000002 , 0xFFFF);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ByteArrayOutputStream byteArrayOutputStream() {
        return new ByteArrayOutputStream();
    }

}
