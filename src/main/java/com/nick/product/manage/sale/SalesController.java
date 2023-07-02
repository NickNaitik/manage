package com.nick.product.manage.sale;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("newSale")
    public String newSale(@RequestBody SalesDto salesDto){
        return salesService.addNewSale(salesDto);

    }

}
