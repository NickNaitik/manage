package com.nick.product.manage.Services.masterOperations;

import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Repository.SuppliersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MasterService {

    private static final Logger logger = LoggerFactory.getLogger(MasterService.class);

    private final SuppliersRepository suppliersRepository;

    @Autowired
    public MasterService(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    public ResponseEntity<List<Supplier>> getSuppliers(String master_uid) {
        return ResponseEntity.ok().body(suppliersRepository.getSuppliers(master_uid));
    }

//    public ResponseEntity<String> addSupplier(Supplier supplier) {
//        //validate Request parameters using validator class create a method in validation class
//        Supplier s = suppliersRepository.save(supplier);
//        return ResponseEntity.accepted().body("Supplier added successfully! , Please note the supplier Id : " +s.getSupplier_uid() + "and Supplier auto generated password is : "+s.getSupplier_password());
//    }
    //update supplier Details


}
