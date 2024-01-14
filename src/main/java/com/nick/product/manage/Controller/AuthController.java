package com.nick.product.manage.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Services.AuthenticationService;
import com.nick.product.manage.Token.Token;
import com.nick.product.manage.Token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/getToken")
    public ResponseEntity<String> getToken(@RequestBody JsonNode request) {
        String supplierId = String.valueOf(request.get("supplier_Id"));
        String supplierPassword = String.valueOf(request.get("supplier_Password"));
        String jwt = authenticationService.getToken(supplierId, supplierPassword);

        if(jwt !=  null) {

            Supplier supplier = authenticationService.getSupplierById(supplierId);
            Token token = Token.builder()
                    .supplier(supplier)
                    .token(jwt)
                    .revoked(false)
                    .expired(false)
                    .tokenType(TokenType.BEARER)
                    .build();
            authenticationService.revokeAllUserTokens(supplier);
            authenticationService.saveToken(token);
            supplier.setToken(token);
            return ResponseEntity.ok(jwt);
        } else {
            return ResponseEntity.badRequest().body("UserId or Password is wrong!!");
        }
    }
}
