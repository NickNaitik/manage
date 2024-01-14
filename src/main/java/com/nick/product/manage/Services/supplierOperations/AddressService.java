package com.nick.product.manage.Services.supplierOperations;

import com.nick.product.manage.Entity.Address;
import com.nick.product.manage.Entity.Customer;
import com.nick.product.manage.Repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    List<String> addressList = new ArrayList<>();


    public ResponseEntity<String> addAddress(Address address) {
        Address adr = addressRepository.save(address);
        return ResponseEntity.ok().body("Address Added Successfully!!, Please Note Address_uid : "+ adr.getAddress_uid());
    }


    public ResponseEntity<List<String>> getSupplierAddresses(String supplierUid) {
        //check krna pd skta hai ki address pehle se available to hai nhi
        addressList = addressRepository.findSupplierAddresses(supplierUid);
        return ResponseEntity.ok().body(addressList);
    }
}
