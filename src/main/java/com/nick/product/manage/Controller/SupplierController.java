package com.nick.product.manage.Controller;

import com.nick.product.manage.Entity.Address;
import com.nick.product.manage.Entity.Customer;
import com.nick.product.manage.Services.supplierOperations.AddressService;
import com.nick.product.manage.Services.supplierOperations.CustomersService;
import com.nick.product.manage.Services.supplierOperations.ProductsService;
import com.nick.product.manage.Services.supplierOperations.SalesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin("*")
public class SupplierController {

    @Autowired
    SalesService salesService;

    @Autowired
    CustomersService customersService;

    @Autowired
    ProductsService productsService;

    @Autowired
    AddressService addressService;

    @PostMapping(path = "/addCustomer")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        System.out.println("Received Body : " +customer);
        return customersService.addNewCustomer(customer);
    }

    @GetMapping("/getCustomers/{sup_id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Customer>> getSupplierCustomers(@PathVariable("sup_id") String supplier_uid) {
        return customersService.getSupplierCustomers(supplier_uid);
    }

    @PostMapping(path="/addAddress")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> addAddress(@RequestBody Address address) {
        System.out.println("Received Body : "+address);
        return addressService.addAddress(address);
    }

    @GetMapping("/getAddresses/{sup_id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<String>> getSupplierAddresses(@PathVariable("sup_id") String supplier_uid){
        System.out.println("Supplier Id : "+supplier_uid);
        return addressService.getSupplierAddresses(supplier_uid);
    }

}
