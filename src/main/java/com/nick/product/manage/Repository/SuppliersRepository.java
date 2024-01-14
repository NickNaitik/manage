package com.nick.product.manage.Repository;

import com.nick.product.manage.Entity.Product;
import com.nick.product.manage.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, String> {

    @Query("SELECT s FROM Supplier s WHERE s.master_uid = ?1")
    List<Supplier> getSuppliers(String master_uid);

    @Query("SELECT s FROM Supplier s WHERE s.supplier_uid = ?1")
    Optional<Supplier> getSupplierById(String supplier_uid);

    @Query("SELECT s FROM Supplier s WHERE s.supplier_uid = 'SUP101' AND s.supplier_password = 'user1'")
    Optional<Supplier> getSupplierByIdAndPassword(String supplier_uid, String supplier_password);

}
