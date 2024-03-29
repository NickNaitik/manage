package com.nick.product.manage.sale;

import com.nick.product.manage.customer.Customer;
import com.nick.product.manage.customer.CustomersRepository;
import com.nick.product.manage.product.Product;
import com.nick.product.manage.product.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class SaleValidator {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    public ResponseEntity<String> validateSale(SalesDto salesDto){

        ResponseEntity<String> response = null;
        String responseBody = null;

        if(!customersRepository.existsById(salesDto.getSale_Cus_Id())){

            responseBody =  "Customer id does not exist!";

        } else {

            Map<String, Integer> map = salesDto.getSale_Details();

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String productName = entry.getKey();
                Integer productQuantity = entry.getValue();

                // Do something with key and value
                Optional<Product> product = productsRepository.findProductByName(productName);

                if(product.isPresent()) {

                    if(product.get().getProd_Available() < productQuantity) {
                        responseBody = productName + " quantity is low, only "+product.get().getProd_Available()+ " available.";
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