package com.nick.product.manage.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> getAllProducts(){
        return productsService.getAllProducts();
    }

    @GetMapping(path = "{supplier_uid}")
    public ResponseEntity<List<Product>> getSupplierProducts(@PathVariable("supplier_uid") String supplier_uid){
        //Validate supplierId
        return productsService.getSupplierProducts(supplier_uid);
    }

    @PostMapping("addNewProduct")
    public ResponseEntity<String> addNewProduct(@RequestBody Product product){
        //validate  Request parameters
        return productsService.addNewProduct(product);
    }

    @PutMapping(path = "{supplierUid}/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Long prod_Id,
                                     @PathVariable("supplierUid") String supplierUid,
                                     @RequestParam(required = false) Long prod_Available,
                                     @RequestParam(required = false) Double prod_salesPrice,
                                     @RequestParam(required =false) Double prod_costPrice) {
        return productsService.updateProduct(prod_Id,supplierUid,prod_Available,prod_salesPrice, prod_costPrice,0L);
    }

    @DeleteMapping(path ="{supplierUid}/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long prod_Id,
                                                @PathVariable ("supplierUid") String supplierUid) {
        return productsService.deleteProduct(prod_Id,supplierUid);
    }


}
