package com.nick.product.manage.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CustomersService {

    private static final Logger logger = LoggerFactory.getLogger(CustomersService.class);

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersService(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }

    public String addNewCustomer(Customer customer){
        Optional<Customer> cust1 = customersRepository.findCustomerBYMobileAndSupplier(customer.getCus_mobile(), customer.getSupplier_uid());

        if(cust1.isPresent()){
            throw new IllegalCallerException("Mobile Number Already Registered");
        }

        Customer cust2 = customersRepository.save(customer);
        //Optional<Customer> cust2 = customersRepository.findCustomerByMobile(customer.getCus_mobile());
        return "Customer Added Successfully!!, Please Note CustomerId : "+ cust2.getCus_uid();
    }

    public List<Customer> getAllCustomer() {
        return customersRepository.findAll();
    }

    @Transactional
    public String updateCustomer(Long cusId, String supplier_uid, String cusMobile, String cusEmail, Double cusDueAmount, Double cusAdvAmount, Double cusBuyAmount) {

        logger.info("cusDueAmount : "+ cusDueAmount);
        logger.info("cusId : "+ cusId);
        logger.info("supplier_uid : "+ supplier_uid);
        logger.info("cusMobile : "+ cusMobile);
        Customer customer = customersRepository.findCustomerofSupplier(supplier_uid, cusId).orElseThrow(() -> new IllegalStateException(
                "Customer with id "+ cusId + " does not exist for you!!"));

        String response = "Nothing Updated!";
        if(cusMobile != null && !Objects.equals(customer.getCus_mobile(),cusMobile)){
            Optional<Customer> custByMobile = customersRepository.findCustomerByMobile(cusMobile);

            if(custByMobile.isPresent()){
                throw new IllegalArgumentException("This mobile number is already registered with customerId : "
                        +custByMobile.get().getCus_uid());
            }
            customer.setCus_mobile(cusMobile);
            response = "Customer Mobile Number is Successfully Updated!";
        }

        if(cusEmail != null && !Objects.equals(customer.getCus_email(), cusEmail)){
            customer.setCus_email(cusEmail);
            response = "Customer Email is Successfully Updated!";
        }

        if(cusDueAmount != null && cusDueAmount != 0){

            if(cusDueAmount >= customer.getCus_advAmount()) {
                cusDueAmount = cusDueAmount - customer.getCus_advAmount();
                customer.setCus_advAmount(0.0);
            }

            if(cusDueAmount < customer.getCus_advAmount()){
                customer.setCus_advAmount(customer.getCus_advAmount() - cusDueAmount);
                cusDueAmount = 0.0;
                customer.setCus_dueAmount(cusDueAmount);
            }

            cusDueAmount = customer.getCus_dueAmount() + cusDueAmount;
            customer.setCus_dueAmount(cusDueAmount);
            response = "Customer Due Amount is Successfully Updated!";
        }

        if(cusAdvAmount != null && cusAdvAmount != 0){

            if(cusAdvAmount != 0.0 && cusAdvAmount > customer.getCus_dueAmount()) {
                cusAdvAmount = cusAdvAmount-customer.getCus_dueAmount();
                customer.setCus_dueAmount(0.0);
            }
            if(cusAdvAmount != 0.0 && cusAdvAmount <= customer.getCus_dueAmount()) {
                customer.setCus_dueAmount(customer.getCus_dueAmount()-cusAdvAmount);
                cusAdvAmount = 0.0;
                customer.setCus_advAmount(cusAdvAmount);
            }

            cusAdvAmount = customer.getCus_advAmount() + cusAdvAmount;
            customer.setCus_advAmount(cusAdvAmount);
            response = "Customer Advance Amount is SuccessFully Updated!";
        }

        if(cusBuyAmount != null && cusBuyAmount != 0){
            cusBuyAmount = customer.getCus_buyAmount() + cusBuyAmount;
            customer.setCus_buyAmount(cusBuyAmount);
            response = "Customer Total Buy Amount is SuccessFully Updated!";
        }

        return response;
    }

    public String deleteCustomer(Long cusId,String supplier_uid) {
        Optional<Customer> customer = customersRepository.findCustomerofSupplier(supplier_uid, cusId);

        if(customer.isEmpty()){
            return "Customer with id "+cusId + " does not present for you!!, So you can't delete";
        } else {
            customersRepository.deleteById(cusId);
            return "Customer with Id - " + cusId + " deleted Successfully!";
        }
    }

    public Optional<Customer> getCustomerById(Long cus_uid, String supplier_uid) {
        boolean exists = customersRepository.existsById(cus_uid);

        if(!exists){
            throw new IllegalStateException("Customer with id "+cus_uid + " does not present");
        }

        Optional<Customer> customer = customersRepository.findById(cus_uid);
        if(!customer.get().getSupplier_uid().equals(supplier_uid)){
            throw new IllegalArgumentException("You don't have permission to see this customer!");
        }
        return customer;
    }

    public List<Customer> getSupplierCustomers(String supplierUid) {
        List<Customer> customer = customersRepository.findSupplierCustomers(supplierUid);
        return customer;
    }
}
