package com.nick.product.manage.Repository;

import com.nick.product.manage.Entity.Address;
import com.nick.product.manage.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a.address_fullAddress FROM Address a WHERE a.supplier_uid = ?1")
    List<String> findSupplierAddresses(String supplier_uid);
}
