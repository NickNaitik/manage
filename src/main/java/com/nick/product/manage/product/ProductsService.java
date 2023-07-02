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
        Optional<Product> prod1 = productsRepository.findProductByName(product.getProd_Name());

        if(prod1.isPresent()){
            throw new IllegalArgumentException("This Product is Already Present!");
        }
        productsRepository.save(product);
        Optional<Product> prod2 = productsRepository.findProductByName(product.getProd_Name());
        return "Product added Successfully, Please Note Prod Id : "+prod2.get().getProd_Id();
    }

    public String deleteProduct(Long prodId) {
        boolean exists = productsRepository.existsById(prodId);

        if(!exists){
            throw new IllegalStateException("Product with id "+prodId + " does not present");
        }

        Optional<Product> product = productsRepository.findById(prodId);
        productsRepository.deleteById(prodId);
        return product.get().getProd_Name() +" deleted Successfully!";
    }

    @Transactional
    public String updateProductAvabl(Long prodId, Long prodAvailable, Long prodSold) {
        Product product = productsRepository.findById(prodId).orElseThrow(() -> new IllegalStateException(
                "Product with id "+ prodId + " does not exist"));

        String response = "Nothing Updated !";
        if(prodAvailable != null && prodAvailable>0) {
            prodAvailable = product.getProd_Available() + prodAvailable;
            product.setProd_Available(prodAvailable);
            return response = product.getProd_Name() + " quantity updated!";
        }

        if(prodSold != null && prodSold>0){
            prodSold = product.getProd_Sold() + prodSold;
            product.setProd_Sold(prodSold);

            return "Total Sold product Updated!";
        }
        return response;
    }
}
