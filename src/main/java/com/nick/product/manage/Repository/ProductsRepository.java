package com.nick.product.manage.Repository;

import com.nick.product.manage.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @Query("SELECT s FROM Product s WHERE s.prod_name = ?1")
    Optional<Product> findProductByName(String prod_Name);

    @Query("SELECT p FROM Product p WHERE p.supplier_uid = ?1")
    List<Product> findSupplierProducts(String supplier_uid);

    @Query("SELECT s FROM Product s WHERE s.prod_name = ?1 AND s.supplier_uid =?2")
    Product findProductByNameAndSupplier(String prod_Name, String supplier_uid);

    @Query("SELECT s FROM Product s WHERE s.prod_uid = ?1 AND s.supplier_uid =?2")
    Product findProductOfSupplier(Long prodId, String supplierUid);
}
