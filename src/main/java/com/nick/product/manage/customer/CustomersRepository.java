package com.nick.product.manage.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT s FROM Customer s WHERE s.cus_mobile = ?1")
    Optional<Customer> findCustomerByMobile(String cus_mobile);



}
