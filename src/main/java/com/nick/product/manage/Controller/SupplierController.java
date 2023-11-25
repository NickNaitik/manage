package com.nick.product.manage.Controller;

import com.nick.product.manage.Entity.Customer;
import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Services.supplierOperations.CustomersService;
import com.nick.product.manage.Services.supplierOperations.ProductsService;
import com.nick.product.manage.Services.supplierOperations.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
public class SupplierController {

    @Autowired
    SalesService salesService;

    @Autowired
    CustomersService customersService;

    @Autowired
    ProductsService productsService;


    @PostMapping(path = "{supplier_uid}/addCustomer")
    public ResponseEntity<String> addSupplier(@RequestBody Customer customer){
        return customersService.addNewCustomer(customer);
    }



}
