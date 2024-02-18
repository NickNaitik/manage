package com.nick.product.manage.Services.masterOperations;

import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Repository.SuppliersRepository;
import com.nick.product.manage.Services.AuthenticationService;
import com.nick.product.manage.Services.EmailService;
import com.nick.product.manage.Token.Token;
import com.nick.product.manage.Token.TokenResponse;
import com.nick.product.manage.Token.TokenType;
import com.nick.product.manage.tfa.TwoFactorAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MasterService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    EmailService emailService;

    private final TwoFactorAuthenticationService twoFactorAuthenticationService;

    private final SuppliersRepository suppliersRepository;

    public ResponseEntity<List<Supplier>> getSuppliers(String master_uid) {
        return ResponseEntity.ok().body(suppliersRepository.getSuppliers(master_uid));
    }

    public ResponseEntity<?> addSupplier(Supplier supplier, String master_uid) {

        Supplier supm = suppliersRepository.getSupplierByMobile(supplier.getSupplier_mobile());
        if(supm != null){
            return ResponseEntity.badRequest().body("Supplier with this mobile number already present");
        }
        supplier.setMaster_uid(master_uid);
        supplier.setSupplier_password(UUID.randomUUID().toString());
        supplier.setSecret(twoFactorAuthenticationService.generateNewSecret());
        suppliersRepository.save(supplier);
        if(supplier.getTwoFactorEnabled()) {
            String imageUri = twoFactorAuthenticationService.generateQRCodeImageUri(supplier.getSecret());
            TokenResponse tokenResponse = TokenResponse.builder()
                    .message("Supplier added successfully! , Please note the supplier Id : " +supplier.getSupplier_uid() + " and Supplier auto generated password is : "+supplier.getSupplier_password())
                    .twoFactorEnabled(true)
                    .secretImageUri(imageUri)
                    .build();

            //emailService.sendEmail(supplier.getSupplier_email(),supplier.getSupplier_password(),imageUri);

            return ResponseEntity.ok(tokenResponse);
        } else {
            TokenResponse tokenResponse = TokenResponse.builder()
                    .message("Supplier added successfully! , Please note the supplier Id : " +supplier.getSupplier_uid() + " and Supplier auto generated password is : "+supplier.getSupplier_password())
                    .twoFactorEnabled(false)
                    .build();
            return ResponseEntity.ok(tokenResponse);
        }

    }

}
