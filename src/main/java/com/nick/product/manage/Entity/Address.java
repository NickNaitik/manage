package com.nick.product.manage.Entity;

import javax.persistence.*;

@Entity
@Table
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1,
            initialValue = 1000001
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private Long address_uid;
    private String address_fullAddress;
    private String supplier_uid;

    public Address() {}

    public Address(String address_fullAddress, String supplier_uid) {
        this.address_fullAddress = address_fullAddress;
        this.supplier_uid = supplier_uid;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_uid=" + address_uid +
                ", address_fullAddress='" + address_fullAddress + '\'' +
                ", supplier_uid='" + supplier_uid + '\'' +
                '}';
    }

    public Long getAddress_uid() {
        return address_uid;
    }

    public String getAddress_fullAddress() {
        return address_fullAddress;
    }

    public String getSupplier_uid() {
        return supplier_uid;
    }
}

