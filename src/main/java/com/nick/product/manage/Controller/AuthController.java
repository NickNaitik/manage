package com.nick.product.manage.Controller;

import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Services.AuthenticationService;
import com.nick.product.manage.Token.*;
import com.nick.product.manage.tfa.TwoFactorAuthenticationService;
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
@CrossOrigin("*")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    private final TwoFactorAuthenticationService twoFactorAuthenticationService;

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
        System.out.println("Supplier_id : "+supplierId);
        System.out.println("supplier_password : "+supplierPassword);
        Supplier supplier = authenticationService.getSupplierByIdAndPassword(supplierId, supplierPassword);

        authenticationService.revokeAllUserTokens(supplier);
        System.out.println("2 FA : "+supplier.getTwoFactorEnabled());
        //If two factor enabled then it will not give tokens and instead go for 2FA flow
        if(supplier.getTwoFactorEnabled()){
            TokenResponse tokenResponse = TokenResponse.builder()
                    .accessToken("")
                    .refreshToken("")
                    .twoFactorEnabled(supplier.getTwoFactorEnabled())
                    .build();

            return ResponseEntity.ok(tokenResponse);
        }
        else {
            String accessToken = authenticationService.generateAccessToken(supplierId, supplierPassword);
            String refreshToken = authenticationService.generateRefreshToken(supplierId, supplierPassword);

            if (accessToken != null && refreshToken != null) {

                authenticationService.revokeAllUserTokens(supplier);

                Token access = Token.builder()
                        .supplier(supplier)
                        .token(accessToken)
                        .revoked(false)
                        .expired(false)
                        .tokenType(TokenType.ACCESS)
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
                TokenResponse tokenResponse = TokenResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .twoFactorEnabled(false)
                        .build();

                return ResponseEntity.ok(tokenResponse);
            }
        }
        return null;
    }

/*    @PostMapping("/refreshToken")
    @SecurityRequirement(name = "bearerAuth")
    public void getTokenfromRefreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }*/

    @PostMapping("/refreshToken")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TokenResponse> getNewAccessTokenFromRefreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken
                                         ) throws IOException {
        return authenticationService.refreshToken(refreshToken);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest verificationRequest){

        return ResponseEntity.ok(authenticationService.verify(verificationRequest));

    }

}
