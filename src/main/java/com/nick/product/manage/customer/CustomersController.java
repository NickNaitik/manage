package com.nick.product.manage.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomersController {

    private final CustomersService customersService;

    @Autowired
    public CustomersController(CustomersService customersService){
        this.customersService = customersService;
    }

    @GetMapping("getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customersService.getAllCustomer();
    }

    @GetMapping(path = "{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable("customerId") Long cus_Id) {

        return customersService.getCustomerById(cus_Id);

    }

    @PostMapping("regCustomer")
    public String registerNewCustomer(@RequestBody Customer customer){
        return customersService.addNewCustomer(customer);
    }

    @PutMapping(path = "{customerId}")
    public String updateCustomer(@PathVariable("customerId") Long cus_Id,
                                 @RequestParam(required = false) String cus_mobile,
                                 @RequestParam(required = false) String cus_email,
                                 @RequestParam(required = false) Double cus_dueAmount,
                                 @RequestParam(required = false) Double cus_advAmount,
                                 @RequestParam(required = false) Double cus_buyAmount
                                 ) {
        return customersService.updateCustomer(cus_Id, cus_mobile, cus_email, cus_dueAmount, cus_advAmount,cus_buyAmount);
    }

    @DeleteMapping(path ="{customerId}")
    public String deleteCustomer(@PathVariable("customerId") Long cus_Id){
        return customersService.deleteCustomer(cus_Id);
    }


}
