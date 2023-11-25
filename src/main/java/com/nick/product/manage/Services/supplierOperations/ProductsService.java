package com.nick.product.manage.Services.supplierOperations;

import com.nick.product.manage.Entity.Product;
import com.nick.product.manage.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }


    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productsRepository.findAll());
    }

    public ResponseEntity<String> addNewProduct(Product product) {

        ResponseEntity<String> response;
        Product prod1 = productsRepository.findProductByNameAndSupplier(product.getProd_name(), product.getSupplier_uid());

        if(prod1 != null) {
            response = ResponseEntity.badRequest().body("This Product is Already Present!");
        } else{
            Product prod2 = productsRepository.save(product);
            response = ResponseEntity.ok().body("Product added Successfully, Please Note Product Id : "+prod2.getProd_uid());
        }
        return  response;
    }

    public ResponseEntity<String> deleteProduct(Long prodId, String supplierUid) {
        Product product = productsRepository.findProductOfSupplier(prodId, supplierUid);

        ResponseEntity<String> response;

        if(product == null){
            response = ResponseEntity.badRequest().body("Product with id "+prodId + " does not present for you!");
        } else {

            productsRepository.deleteById(product.getProd_uid());
            response = ResponseEntity.ok().body(product.getProd_name() +" deleted Successfully!");
        }
        return response;
    }

    @Transactional
    public ResponseEntity<String> updateProduct(Long prodId, String supplierUid, Long prodAvailable, Double prod_salesPrice, Double prod_costPrice, Long prodSold) {

        ResponseEntity<String> response = ResponseEntity.badRequest().body("Nothing Updated!");

        Product product = productsRepository.findProductOfSupplier(prodId,supplierUid);

        if(product == null){
            ResponseEntity.badRequest().body("Product with id "+ prodId + " does not exist for you!!");
        } else {
            if (prodAvailable != null && prodAvailable > 0) {
                prodAvailable = product.getProd_avail() + prodAvailable;
                product.setProd_avail(prodAvailable);
                response = ResponseEntity.ok().body(product.getProd_name() + " quantity updated! ");
            }

            if (prod_salesPrice != 0) {
                product.setProd_salesPrice(prod_salesPrice);
                response = ResponseEntity.ok().body( response + product.getProd_name() + " sales price Updated!");
            }

            if (prod_costPrice != 0.0) {
                product.setProd_costPrice(prod_costPrice);
                response = ResponseEntity.ok().body( response + product.getProd_name() + " cost price Updated!");
            }

            if (prodSold != null && prodSold > 0) {
                prodAvailable = product.getProd_avail() - prodSold;
                product.setProd_avail(prodAvailable);
                prodSold = product.getProd_sold() + prodSold;
                product.setProd_sold(prodSold);

                response = ResponseEntity.ok().body("Total Sold product Updated!");
            }
        }
        return response;
    }

    public ResponseEntity<List<Product>> getSupplierProducts(String supplierUid) {
        return ResponseEntity.ok().body(productsRepository.findSupplierProducts(supplierUid));
    }
}
