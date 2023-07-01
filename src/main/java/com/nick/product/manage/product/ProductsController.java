package com.nick.product.manage.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping("getAllProducts")
    public List<Product> getAllProducts(){
        return productsService.getAllProducts();
    }

    @PostMapping("addNewProduct")
    public String addNewProduct(@RequestBody Product product){
        return productsService.addNewProduct(product);
    }

    @DeleteMapping(path ="{productId}")
    public String deleteProduct(@PathVariable("productId") Long prod_Id) {
        return productsService.deleteProduct(prod_Id);
    }

    @PutMapping(path = "{productId}")
    public String updateProductAvabl(@PathVariable("productId") Long prod_Id,
                                     @RequestParam(required = false) Long prod_Available,
                                     @RequestParam(required = false) Long prod_Sold){
        return productsService.updateProductAvabl(prod_Id,prod_Available,prod_Sold);
    }

}
