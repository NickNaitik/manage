package com.nick.product.manage.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersService(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }

    public String addNewCustomer(Customer customer){
        Optional<Customer> cust1 = customersRepository.findCustomerByMobile(customer.getCus_mobile());

        if(cust1.isPresent()){
            throw new IllegalCallerException("Mobile Number Already Registered");
        }
        customersRepository.save(customer);
        Optional<Customer> cust2 = customersRepository.findCustomerByMobile(customer.getCus_mobile());
        return "Customer Added Successfully!!, Please Note CustomerId : "+ cust2.get().getCus_Id();
    }

    public List<Customer> getAllCustomer() {
        return customersRepository.findAll();
    }

    @Transactional
    public String updateCustomer(Long cusId, String cusMobile, String cusEmail, Double cusDueAmount, Double cusAdvAmount, Double cusBuyAmount) {

        Customer customer = customersRepository.findById(cusId).orElseThrow(() -> new IllegalStateException(
                "Customer with id "+ cusId + " does not exist"));

        String response = "";
        if(cusMobile != null && !Objects.equals(customer.getCus_mobile(),cusMobile)){
            Optional<Customer> custByMobile = customersRepository.findCustomerByMobile(cusMobile);

            if(custByMobile.isPresent()){
                throw new IllegalArgumentException("This mobile number is already registered with customerId : "
                        +custByMobile.get().getCus_Id());
            }
            customer.setCus_mobile(cusMobile);
            response = "Customer Mobile Number is Successfully Updated!";
        }

        if(cusEmail != null && !Objects.equals(customer.getCus_email(), cusEmail)){
            customer.setCus_email(cusEmail);
            response = "Customer Email is Successfully Updated!";
        }

        if(cusDueAmount != null && !Objects.equals(customer.getCus_dueAmount(),cusDueAmount)){

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

        if(cusAdvAmount != null && !Objects.equals(customer.getCus_advAmount(), cusAdvAmount)){

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

        if(cusBuyAmount != null && !Objects.equals(customer.getCus_buyAmount(),cusBuyAmount)){
            customer.setCus_buyAmount(cusBuyAmount);
            response = "Customer Total Buy Amount is SuccessFully Updated!";
        }

        return response;
    }

    public String deleteCustomer(Long cusId) {
        boolean exists = customersRepository.existsById(cusId);

        if(!exists){
            throw new IllegalStateException("Customer with id "+cusId + " does not present");
        }

        customersRepository.deleteById(cusId);
        return "Customer with Id - "+cusId +" deleted Successfully!";
    }

    public Optional<Customer> getCustomerById(Long cusId) {
        boolean exists = customersRepository.existsById(cusId);

        if(!exists){
            throw new IllegalStateException("Customer with id "+cusId + " does not present");
        }

        Optional<Customer> customer = customersRepository.findById(cusId);
        return customer;
    }
}
