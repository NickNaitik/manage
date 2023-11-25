package com.nick.product.manage.Util;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @GetMapping(value = "/{amount}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCodeImage(
            @PathVariable("amount") String amount
    ) throws WriterException, IOException {
        String qrText = "upi://pay?pa=" + "8757394648@ybl" + "&pn=Your%20Name&mc=1234&tid=123456&tr=0123456789&tn=Payment%20Description&am=" + amount;

        int width = 300;
        int height = 300;

        byte[] qrCodeImage = qrCodeGenerator.generateQRCodeImage(qrText, width, height);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);
    }
}
