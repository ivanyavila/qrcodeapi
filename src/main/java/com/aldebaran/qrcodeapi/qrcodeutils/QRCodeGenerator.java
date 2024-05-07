package com.aldebaran.qrcodeapi.qrcodeutils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@AllArgsConstructor
public class QRCodeGenerator {

    private final QRCodeWriter qrCodeWriter;
    private final MatrixToImageConfig matrixToImageConfig;
    private final ByteArrayOutputStream pngOutputStream;
    private final Environment environment;

    public byte[] getQRCodeBytes(String ulid) throws WriterException, IOException {
        int imageDimension = 250;
        String imageFormat = "PNG";
        MatrixToImageWriter.writeToStream(
                qrCodeWriter.encode(getURL()+ulid, BarcodeFormat.QR_CODE, imageDimension, imageDimension),
                imageFormat,
                pngOutputStream,
                matrixToImageConfig);
        return pngOutputStream.toByteArray();
    }

    public String getURL() throws UnknownHostException {
        //TODO: Change the way to get the HostName (domain in prod), Port (only for dev env) and endpoint
        String readQRCodeEndpoint = "qrcode/read/";
        String port = environment.getProperty("local.server.port");
        String hostname = String.valueOf(InetAddress.getLocalHost());
        String host = "http://"+hostname+":"+port+"/";
        return host + readQRCodeEndpoint; // Should return -> http://[hostname:port|domain]/qrcode/read/
    }
}
