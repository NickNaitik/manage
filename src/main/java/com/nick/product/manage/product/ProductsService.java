package com.nick.product.manage.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }



    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public String addNewProduct(Product product) {
        Optional<Product> prod1 = productsRepository.findProductByNameAndSupplier(product.getProd_name(), product.getSupplier_uid());

        if(prod1.isPresent()){
            throw new IllegalArgumentException("This Product is Already Present!");
        }
        Product prod2 = productsRepository.save(product);
        //Optional<Product> prod2 = productsRepository.findProductByName(product.getProd_name());
        return "Product added Successfully, Please Note Product Id : "+prod2.getProd_uid();
    }

    public String deleteProduct(Long prodId) {
        boolean exists = productsRepository.existsById(prodId);

        if(!exists){
            throw new IllegalStateException("Product with id "+prodId + " does not present");
        }

        Optional<Product> product = productsRepository.findById(prodId);
        productsRepository.deleteById(prodId);
        return product.get().getProd_name() +" deleted Successfully!";
    }

    @Transactional
    public String updateProductAvabl(Long prodId, String supplierUid, Long prodAvailable, Double prod_salesPrice, Double prod_costPrice, Long prodSold) {
        List<Product> product = productsRepository.findSupplierProducts(supplierUid);

        if(product.isEmpty()){
            return "Product with id "+ prodId + " does not exist for you!!";
        }

        String response = "";
        if(prodAvailable != null && prodAvailable>0) {
            prodAvailable = product.get(0).getProd_avail() + prodAvailable;
            product.get(0).setProd_avail(prodAvailable);
            response = product.get(0).getProd_name() + " quantity updated! ";
        }

        if(prod_salesPrice != 0){
            product.get(0).setProd_salesPrice(prod_salesPrice);
            response = response + product.get(0).getProd_name() + " sales price Updated!";
        }

        if(prod_costPrice != 0.0){
            product.get(0).setProd_costPrice(prod_costPrice);
            response = response + product.get(0).getProd_name() + " cost price Updated!";
        }

        if(prodSold != null && prodSold>0){
            prodAvailable = product.get(0).getProd_avail()-prodSold;
            product.get(0).setProd_avail(prodAvailable);
            prodSold = product.get(0).getProd_sold() + prodSold;
            product.get(0).setProd_sold(prodSold);

            response =  "Total Sold product Updated!";
        }
        return response;
    }

    public List<Product> getSupplierProducts(String supplierUid) {
        return productsRepository.findSupplierProducts(supplierUid);
    }
}
