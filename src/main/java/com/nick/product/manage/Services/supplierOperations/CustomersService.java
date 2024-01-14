package com.nick.product.manage.Services.supplierOperations;

import com.nick.product.manage.Entity.Customer;
import com.nick.product.manage.Repository.CustomersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomersService {

    private static final Logger logger = LoggerFactory.getLogger(CustomersService.class);

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersService(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }

    public ResponseEntity<String> addNewCustomer(Customer customer){
        Customer cust1 = customersRepository.findCustomerBYMobileAndSupplier(customer.getCus_mobile(), customer.getSupplier_uid());

        if(cust1 != null){
             return ResponseEntity.badRequest().body("Mobile Number Already Registered with customer Id : "+cust1.getCus_uid());
        } else {
            Customer cust2 = customersRepository.save(customer);
            return ResponseEntity.ok().body("Customer Added Successfully!!, Please Note CustomerId : "+ cust2.getCus_uid());
        }
    }


    @Transactional
    public ResponseEntity<String> updateCustomer(Long cusId, String supplier_uid, String cusMobile, String cusEmail, Double cusDueAmount, Double cusAdvAmount, Double cusBuyAmount) {

        ResponseEntity<String> response = ResponseEntity.badRequest().body("Nothing Updated!");

        Customer customer = customersRepository.findCustomerofSupplier(supplier_uid, cusId);

        if(customer != null) {

            if (!cusMobile.isBlank()) {
                Customer cust1 = customersRepository.findCustomerBYMobileAndSupplier(cusMobile,supplier_uid);

                if (cust1 != null) {
                    response = ResponseEntity.badRequest().body("This mobile number is already registered with customerId : "
                            + cust1.getCus_uid().toString());
                } else {
                    customer.setCus_mobile(cusMobile);
                    response = ResponseEntity.accepted().body("Customer Mobile Number is Successfully Updated!");
                }
            }

            if (!cusEmail.isBlank()) {
                customer.setCus_email(cusEmail);
                response = ResponseEntity.accepted().body("Customer Email is Successfully Updated!");
            }

            if (cusDueAmount != null && cusDueAmount != 0) {

                if (cusDueAmount >= customer.getCus_advAmount()) {
                    cusDueAmount = cusDueAmount - customer.getCus_advAmount();
                    customer.setCus_advAmount(0.0);
                }

                if (cusDueAmount < customer.getCus_advAmount()) {
                    customer.setCus_advAmount(customer.getCus_advAmount() - cusDueAmount);
                    cusDueAmount = 0.0;
                    customer.setCus_dueAmount(cusDueAmount);
                }

                cusDueAmount = customer.getCus_dueAmount() + cusDueAmount;
                customer.setCus_dueAmount(cusDueAmount);
                response = ResponseEntity.accepted().body("Customer Due Amount is Successfully Updated!");
            }

            if (cusAdvAmount != null && cusAdvAmount != 0) {

                if (cusAdvAmount > customer.getCus_dueAmount()) {
                    cusAdvAmount = cusAdvAmount - customer.getCus_dueAmount();
                    customer.setCus_dueAmount(0.0);
                }
                if (cusAdvAmount <= customer.getCus_dueAmount()) {
                    customer.setCus_dueAmount(customer.getCus_dueAmount() - cusAdvAmount);
                    cusAdvAmount = 0.0;
                    customer.setCus_advAmount(cusAdvAmount);
                }

                cusAdvAmount = customer.getCus_advAmount() + cusAdvAmount;
                customer.setCus_advAmount(cusAdvAmount);
                response = ResponseEntity.accepted().body("Customer Advance Amount is SuccessFully Updated!");
            }

            if (cusBuyAmount != null && cusBuyAmount != 0) {
                cusBuyAmount = customer.getCus_buyAmount() + cusBuyAmount;
                customer.setCus_buyAmount(cusBuyAmount);
                response = ResponseEntity.accepted().body("Customer Total Buy Amount is SuccessFully Updated!");
            }
        } else {
            response = ResponseEntity.badRequest().body("Customer with id " + cusId + " does not exist for you!!");
        }

        return response;
    }

    public ResponseEntity<String> deleteCustomer(Long cusId, String supplier_uid) {
        Customer customer = customersRepository.findCustomerofSupplier(supplier_uid, cusId);

        if(customer == null){
            return ResponseEntity.badRequest().body("Customer with id "+cusId + " does not present for you!!, So you can't delete");
        } else {
            customersRepository.deleteById(cusId);
            return ResponseEntity.ok().body("Customer with Id - " + cusId + " deleted Successfully!");
        }
    }

    public ResponseEntity<Customer> getCustomerById(Long cus_uid, String supplier_uid) {
        boolean exists = customersRepository.existsById(cus_uid);

        if(!exists){
            ResponseEntity.badRequest().body("Customer with id "+cus_uid + " does not present");
        }

        Customer customer = customersRepository.findCustomerofSupplier(supplier_uid,cus_uid);
        if(!customer.getSupplier_uid().equals(supplier_uid)){
            ResponseEntity.badRequest().body("You don't have permission to see this customer!");
        }
        return ResponseEntity.accepted().body(customer);
    }

    public ResponseEntity<List<Customer>> getSupplierCustomers(String supplierUid) {
        List<Customer> customer = customersRepository.findSupplierCustomers(supplierUid);
        return ResponseEntity.ok().body(customer);
    }
}
