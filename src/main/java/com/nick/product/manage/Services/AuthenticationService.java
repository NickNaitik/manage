package com.nick.product.manage.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Excption.CustomException;
import com.nick.product.manage.Repository.SuppliersRepository;
import com.nick.product.manage.Repository.TokenRepository;
import com.nick.product.manage.Security.JwtService;
import com.nick.product.manage.Token.Token;
import com.nick.product.manage.Token.TokenResponse;
import com.nick.product.manage.Token.TokenType;
import com.nick.product.manage.Token.VerificationRequest;
import com.nick.product.manage.tfa.TwoFactorAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final SuppliersRepository suppliersRepository;

    private final TokenRepository tokenRepository;
    private final TwoFactorAuthenticationService twoFactorAuthenticationService;


    @Autowired
    private JwtService jwtService;

    public String generateAccessToken(String supplierId, String supplierPassword) {
        //Below line call is not needed as we are doing this check in controller
        Optional<Supplier> supplier = suppliersRepository.getSupplierByIdAndPassword(supplierId, supplierPassword);
        String jwtToken =null;
        if(supplier.isPresent()) {
            jwtToken = jwtService.generateToken(supplier.get());
        }
        return jwtToken;
    }
    public String generateRefreshToken(String supplierId, String supplierPassword) {
        //Below line call is not needed as we are doing this check in controller
        Optional<Supplier> supplier = suppliersRepository.getSupplierByIdAndPassword(supplierId, supplierPassword);
        String jwtToken =null;
        if(supplier.isPresent()) {
            jwtToken = jwtService.generateRefreshToken(supplier.get());
        }
        return jwtToken;
    }

    public void revokeAllUserTokens(Supplier supplier){
        var validSupplierToken = tokenRepository.findAllValidTokenBySupplier(supplier.getSupplier_uid());
        if(validSupplierToken.isEmpty())
            return;
        validSupplierToken.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validSupplierToken);
    }

    public void revokeAccessUserTokens(Supplier supplier) {
        var validSupplierToken = tokenRepository.findValidAccessTokenBySupplier(supplier.getSupplier_uid());
        if(validSupplierToken.isEmpty())
            return;
        validSupplierToken.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validSupplierToken);
    }

    public Supplier getSupplierByIdAndPassword(String id, String password){
        try {
            Optional<Supplier> supplier = suppliersRepository.getSupplierByIdAndPassword(id, password);
            return supplier.get();
        } catch (Exception ex){
            throw new CustomException("Invalid username & Password!");
        }

    }


/*    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String userId;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userId = jwtService.extractUserId(refreshToken);
        if(userId != null){
            UserDetails userDetails = suppliersRepository.getSupplierById(userId);
            Supplier supplier = suppliersRepository.getSupplierById(userId);

            var isTokenValid = tokenRepository.findByToken(refreshToken)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if(jwtService.isTokenValid(refreshToken,userDetails) && isTokenValid) {

                //Revoking all active access token
                revokeAccessUserTokens(supplier);

                String newAccessToken = jwtService.generateToken(userDetails);
                Token token = Token.builder()
                        .supplier(supplier)
                        .token(newAccessToken)
                        .revoked(false)
                        .expired(false)
                        .tokenType(TokenType.ACCESS)
                        .build();
                tokenRepository.save(token);
                TokenResponse tokenResponse = TokenResponse.builder()
                        .refreshToken(refreshToken)
                        .accessToken(newAccessToken)
                        .build();
                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                if(newAccessToken == null || newAccessToken.isEmpty())
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                else
                    response.setStatus(HttpStatus.CREATED.value());

                new ObjectMapper().writeValue(response.getOutputStream(), tokenResponse);
            }
        }
    }*/

    public ResponseEntity<TokenResponse> refreshToken(String refresh) {

        final String refreshToken;
        final String userId;

        if(refresh == null || !refresh.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(null);
        }
        refreshToken = refresh.substring(7);
        userId = jwtService.extractUserId(refreshToken);
        if(userId != null){
            UserDetails userDetails = suppliersRepository.getSupplierById(userId);
            Supplier supplier = suppliersRepository.getSupplierById(userId);

            var isTokenValid = tokenRepository.findByToken(refreshToken)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if(jwtService.isTokenValid(refreshToken,userDetails) && isTokenValid && !jwtService.isTokenTypeAccess(refreshToken)) {

                //Revoking all active access token
                revokeAccessUserTokens(supplier);

                String newAccessToken = jwtService.generateToken(userDetails);
                Token token = Token.builder()
                        .supplier(supplier)
                        .token(newAccessToken)
                        .revoked(false)
                        .expired(false)
                        .tokenType(TokenType.ACCESS)
                        .build();
                saveToken(token);
                TokenResponse tokenResponse = TokenResponse.builder()
                        .refreshToken(refreshToken)
                        .accessToken(newAccessToken)
                        .build();
                if(newAccessToken == null || newAccessToken.isEmpty())
                    return  ResponseEntity.badRequest().body(null);
                else
                    return  ResponseEntity.ok().body(tokenResponse);

            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    public TokenResponse verify(VerificationRequest verificationRequest) {
        //Supplier supplier = suppliersRepository.getSupplierByEmail(verificationRequest.getEmail());
        Optional<Supplier> supplier = suppliersRepository.findById(verificationRequest.getSupplier_id());
        if(supplier.isEmpty()){
            throw new CustomException("Supplier not found with supplier Id : "+verificationRequest.getSupplier_id());
        }

        if(twoFactorAuthenticationService.isOtpNotValid(supplier.get().getSecret(), verificationRequest.getCode())) {
            throw new CustomException("Code is not correct");
        }
        revokeAllUserTokens(supplier.get());
        var accessToken = jwtService.generateToken(supplier.get());
        var refreshToken = jwtService.generateRefreshToken(supplier.get());

        Token access = Token.builder()
                .supplier(supplier.get())
                .token(accessToken)
                .revoked(false)
                .expired(false)
                .tokenType(TokenType.ACCESS)
                .build();
        Token refresh = Token.builder()
                .supplier(supplier.get())
                .token(refreshToken)
                .revoked(false)
                .expired(false)
                .tokenType(TokenType.REFRESH)
                .build();
        saveToken(access);
        saveToken(refresh);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .message("Verified Successfully !!, Redirecting in 3 Seconds..")
                .twoFactorEnabled(supplier.get().getTwoFactorEnabled())
                .build();
    }

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }
}