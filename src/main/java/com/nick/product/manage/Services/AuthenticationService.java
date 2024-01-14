package com.nick.product.manage.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Repository.SuppliersRepository;
import com.nick.product.manage.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final SuppliersRepository suppliersRepository;

    public AuthenticationService(SuppliersRepository suppliersRepository) {
        this.suppliersRepository =suppliersRepository;
    }

    @Autowired
    private JwtService jwtService;

    public String getToken(String supplierId, String supplierPassword) {
        Optional<Supplier> supplier = suppliersRepository.getSupplierByIdAndPassword(supplierId, supplierPassword);
        String jwtToken =null;
        if(supplier.isPresent()) {
            jwtToken = jwtService.generateToken(supplier.get());
        }
        return jwtToken;
    }

}
