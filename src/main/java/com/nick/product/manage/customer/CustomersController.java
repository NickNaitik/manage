package com.nick.product.manage.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //This method only master user can call, need to write logic or any authorization things
    @GetMapping("getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return customersService.getAllCustomer();
    }

    //Suppliers can see all the customers under them only
    @GetMapping(path = "{supplier_uid}")
    public ResponseEntity<List<Customer>> getSupplierCustomers(@PathVariable("supplier_uid") String supplier_uid){
        // Validate SupplierId
        return customersService.getSupplierCustomers(supplier_uid);
    }

    @GetMapping(path = "/{supplierUid}/{customerUid}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerUid") Long cus_uid,
                                              @PathVariable("supplierUid") String supplier_uid) {

        return customersService.getCustomerById(cus_uid, supplier_uid);

    }

    @PostMapping("regCustomer")
    public ResponseEntity<String> registerNewCustomer(@RequestBody Customer customer){
        // validate request Body
        return customersService.addNewCustomer(customer);
    }

    @PutMapping(path = "/{supplierUid}/{customerUid}")
    public ResponseEntity<String> updateCustomer(@PathVariable("supplierUid") String supplier_uid,
                                 @PathVariable("customerUid") Long cus_Id,
                                 @RequestParam(required = false) String cus_mobile,
                                 @RequestParam(required = false) String cus_email,
                                 @RequestParam(required = false) Double cus_dueAmount,
                                 @RequestParam(required = false) Double cus_advAmount,
                                 @RequestParam(required = false) Double cus_buyAmount
                                 ) {
        return customersService.updateCustomer(cus_Id, supplier_uid, cus_mobile, cus_email, cus_dueAmount, cus_advAmount,cus_buyAmount);
    }

    @DeleteMapping(path ="{supplierUid}/{customerUid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerUid") Long cus_Id,
                                         @PathVariable("supplierUid") String supplier_uid){
        return customersService.deleteCustomer(cus_Id, supplier_uid);
    }


}
