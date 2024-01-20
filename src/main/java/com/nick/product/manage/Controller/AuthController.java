package com.nick.product.manage.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Services.AuthenticationService;
import com.nick.product.manage.Token.Token;
import com.nick.product.manage.Token.TokenRequest;
import com.nick.product.manage.Token.TokenResponse;
import com.nick.product.manage.Token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/accessToken")
    public ResponseEntity<TokenResponse> getToken(@RequestBody TokenRequest request) {
        String supplierId = String.valueOf(request.getSupplier_Id());
        String supplierPassword = String.valueOf(request.getSupplier_Password());
        String accessToken = authenticationService.generateAccessToken(supplierId, supplierPassword);
        String refreshToken = authenticationService.generateRefreshToken(supplierId, supplierPassword);

        if(accessToken !=  null && refreshToken != null) {

            Supplier supplier = authenticationService.getSupplierById(supplierId);
            authenticationService.revokeAllUserTokens(supplier);

            Token access = Token.builder()
                    .supplier(supplier)
                    .token(accessToken)
                    .revoked(false)
                    .expired(false)
                    .tokenType(TokenType.BEARER)
                    .build();
            Token refresh = Token.builder()
                    .supplier(supplier)
                    .token(refreshToken)
                    .revoked(false)
                    .expired(false)
                    .tokenType(TokenType.REFRESH)
                    .build();

            authenticationService.saveToken(access);
            authenticationService.saveToken(refresh);
            supplier.setToken(access);

            TokenResponse tokenResponse = TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
            //return ResponseEntity.ok(jwt);
            return ResponseEntity.ok(tokenResponse);
        }
        return null;
    }

    @PostMapping("/refreshToken")
    public void getTokenfromRefreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
