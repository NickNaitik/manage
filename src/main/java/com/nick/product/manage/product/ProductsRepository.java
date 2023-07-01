package com.nick.product.manage.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @Query("SELECT s FROM Product s WHERE s.prod_Name = ?1")
    Optional<Product> findProductByName(String prod_Name);
}
