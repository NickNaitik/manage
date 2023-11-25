package com.nick.product.manage.Repository;


import com.nick.product.manage.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.cus_mobile = ?1")
    Optional<Customer> findCustomerByMobile(String cus_mobile);

    @Query("SELECT c FROM Customer c WHERE c.supplier_uid = ?1 AND c.cus_uid =?2")
    Customer findCustomerofSupplier(String supplier_uid, Long cus_uid);

    @Query("SELECT c FROM Customer c WHERE c.supplier_uid = ?1")
    List<Customer> findSupplierCustomers(String supplier_uid);

    @Query("SELECT c FROM Customer c WHERE c.cus_mobile = ?1 AND c.supplier_uid = ?2")
    Customer findCustomerBYMobileAndSupplier(String cus_mobile, String supplier_uid);

}
