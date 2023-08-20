package com.nick.product.manage.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("getSales")
    public List<Sale> getSAllSales(){
        return salesService.gatAllSales();
    }

    @GetMapping(path = "{supplier_uid}")
    public List<Sale> getSupplierSales(@PathVariable("supplier_uid") String supplier_uid){
        return salesService.getSupplierSales(supplier_uid);
    }

    @PostMapping("newSale")
    public ResponseEntity<String> newSale(@RequestBody SalesDto salesDto) {
        return salesService.addNewSale(salesDto);
    }

}
