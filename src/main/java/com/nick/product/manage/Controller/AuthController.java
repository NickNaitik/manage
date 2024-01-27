package com.nick.product.manage.Controller;

import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Services.AuthenticationService;
import com.nick.product.manage.Token.Token;
import com.nick.product.manage.Token.TokenRequest;
import com.nick.product.manage.Token.TokenResponse;
import com.nick.product.manage.Token.TokenType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "AUTHENTICATION CONTROLLER")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @Operation(
            description = "Endpoint to generate the Token",
            summary = "Without this token you can't make any request",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid User name & Password",
                            responseCode = "401"
                    )
            }
    )
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

//    @PostMapping("/refreshToken")
//    @SecurityRequirement(name = "bearerAuth")
//    public void getTokenfromRefreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        authenticationService.refreshToken(request, response);
//    }

    @PostMapping("/refreshToken")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TokenResponse> getNewAccessTokenFromRefreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken
                                         ) throws IOException {
        return authenticationService.refreshToken(refreshToken);
    }

}
