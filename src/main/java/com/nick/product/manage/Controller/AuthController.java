package com.nick.product.manage.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nick.product.manage.Services.AuthenticationService;
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
    public ResponseEntity<String> register (@RequestBody JsonNode request) {
        System.out.println(request.get("supplier_Id"));
        System.out.println(request.get("supplier_Password"));
        String supplierId = String.valueOf(request.get("supplier_Id"));
        String supplierPassword = String.valueOf(request.get("supplier_Password"));
        if(authenticationService.getToken(supplierId, supplierPassword) !=  null)
        {
            return ResponseEntity.ok(authenticationService.getToken(supplierId, supplierPassword));
        } else {
            return ResponseEntity.badRequest().body("UserId or Password is wrong!!");
        }
    }
}
