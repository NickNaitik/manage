package com.nick.product.manage.Repository;


import com.nick.product.manage.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.supplier_uid = ?1")
    List<Sale> getSupplierSales(String supplierUid);
}
