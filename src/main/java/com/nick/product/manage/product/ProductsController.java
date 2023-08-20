package com.nick.product.manage.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "{supplier_uid}")
    public List<Product> getSupplierProducts(@PathVariable("supplier_uid") String supplier_uid){
        return productsService.getSupplierProducts(supplier_uid);
    }

    @PostMapping("addNewProduct")
    public String addNewProduct(@RequestBody Product product){
        return productsService.addNewProduct(product);
    }

    @PutMapping(path = "{supplierUid}/{productId}")
    public String updateProductAvabl(@PathVariable("productId") Long prod_Id,
                                     @PathVariable(required = true) String supplierUid,
                                     @RequestParam(required = false) Long prod_Available,
                                     @RequestParam(required = false) Double prod_salesPrice,
                                     @RequestParam(required =false) Double prod_costPrice) {
        return productsService.updateProductAvabl(prod_Id,supplierUid,prod_Available,prod_salesPrice, prod_costPrice,0L);
    }

    @DeleteMapping(path ="{supplierUid}/{productId}")
    public String deleteProduct(@PathVariable("productId") Long prod_Id) {
        return productsService.deleteProduct(prod_Id);
    }


}
