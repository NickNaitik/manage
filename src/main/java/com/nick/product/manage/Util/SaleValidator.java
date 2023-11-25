package com.nick.product.manage.Util;

import com.nick.product.manage.Dto.SalesDto;
import com.nick.product.manage.Entity.Product;
import com.nick.product.manage.Repository.CustomersRepository;
import com.nick.product.manage.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SaleValidator {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    public ResponseEntity<String> validateSale(SalesDto salesDto){

        ResponseEntity<String> response = null;
        String responseBody = null;

        if(customersRepository.findCustomerofSupplier(salesDto.getSupplier_uid(),salesDto.getSale_Cus_Id()) == null){

            responseBody =  "Customer does not exist for you!";

        } else {

            Map<String, Integer> map = salesDto.getSale_Details();

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String productName = entry.getKey();
                Integer productQuantity = entry.getValue();

                // Do something with key and value
                Product product = productsRepository.findProductByNameAndSupplier(productName, salesDto.getSupplier_uid());

                if(product != null) {

                    if(product.getProd_avail() < productQuantity) {
                        responseBody = productName + " quantity is low, only "+product.getProd_avail()+ " available.";
                    }

                } else {
                    responseBody = "This is not a correct product : "+productName;
                }
            }
        }

        if(responseBody != null) {
            return ResponseEntity.badRequest().body(responseBody);
        } else{
            return ResponseEntity.accepted().body("Validation Passed");
        }
    }

}