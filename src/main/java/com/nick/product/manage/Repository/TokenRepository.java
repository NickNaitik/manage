package com.nick.product.manage.Repository;

import com.nick.product.manage.Token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
    select t from Token t inner join Supplier s on t.supplier.supplier_uid = s.supplier_uid 
    where s.supplier_uid = :supplier_uid and (t.expired= false or t.revoked = false)
""")
    List<Token> findAllValidTokenBySupplier(String supplier_uid);

    @Query("""
    select t from Token t inner join Supplier s on t.supplier.supplier_uid = s.supplier_uid 
    where s.supplier_uid = :supplier_uid and (t.expired= false or t.revoked = false or t.tokenType = 'ACCESS' )
""")
    List<Token> findValidAccessTokenBySupplier(String supplier_uid);
    Optional<Token> findByToken(String token);
}


