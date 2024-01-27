package com.nick.product.manage.Controller;


import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Services.masterOperations.MasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class MasterController {

    private final MasterService masterService;

    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping(path = "{master_uid}/getSupplier")
    public ResponseEntity<List<Supplier>> getSupppliers(@PathVariable("master_uid") String master_uid){
        return masterService.getSuppliers(master_uid);
    }

    @PostMapping(path = "{master_uid}/addSupplier")
    public ResponseEntity<?> addSupplier(@RequestBody Supplier supplier, @PathVariable("master_uid") String master_uid){
        return masterService.addSupplier(supplier, master_uid);
    }

}
