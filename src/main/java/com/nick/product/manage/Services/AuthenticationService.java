package com.nick.product.manage.Services;

import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Repository.SuppliersRepository;
import com.nick.product.manage.Repository.TokenRepository;
import com.nick.product.manage.Security.JwtService;
import com.nick.product.manage.Token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final SuppliersRepository suppliersRepository;

    private final TokenRepository tokenRepository;

    public AuthenticationService(SuppliersRepository suppliersRepository, TokenRepository tokenRepository) {
        this.suppliersRepository =suppliersRepository;
        this.tokenRepository = tokenRepository;
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

    public Supplier getSupplierById(String id){
        return suppliersRepository.getSupplierById(id);

    }

    public Token saveToken(Token token){
        return tokenRepository.save(token);
    }

}
