package com.nick.product.manage.customer;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1,
            initialValue = 1001
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long cus_Id;
    private String cus_name;
    private String cus_shopName;
    private String cus_mobile;
    private String cus_email;
    private String cus_address;
    private Double cus_dueAmount = 0.0;
    private Double cus_advAmount = 0.0;
    private Double cus_buyAmount = 0.0;


    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public void setCus_shopName(String cus_shopName) {
        this.cus_shopName = cus_shopName;
    }

    public void setCus_mobile(String cus_mobile) {
        this.cus_mobile = cus_mobile;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    public void setCus_dueAmount(Double cus_dueAmount) {
        this.cus_dueAmount = cus_dueAmount;
    }

    public void setCus_advAmount(Double cus_advAmount) {
        this.cus_advAmount = cus_advAmount;
    }

    public void setCus_buyAmount(Double cus_buyAmount) {
        this.cus_buyAmount = cus_buyAmount;
    }

    public Long getCus_Id() {
        return cus_Id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public String getCus_shopName() {
        return cus_shopName;
    }

    public String getCus_mobile() {
        return cus_mobile;
    }

    public String getCus_email() {
        return cus_email;
    }

    public String getCus_address() {
        return cus_address;
    }

    public Double getCus_dueAmount() {
        return cus_dueAmount;
    }

    public Double getCus_advAmount() {
        return cus_advAmount;
    }

    public Double getCus_buyAmount() {
        return cus_buyAmount;
    }

    public Customer(){ }


    public Customer(String cusName, String cusShopName, String cusMobile, String cusEmail, String cusAddress) {
        this.cus_name = cusName;
        this.cus_shopName = cusShopName;
        this.cus_mobile = cusMobile;
        this.cus_email = cusEmail;
        this.cus_address = cusAddress;
    }

    public Customer(Long cus_Id, String cus_mobile) {
        this.cus_Id = cus_Id;
        this.cus_mobile = cus_mobile;
    }

    public Customer(Long cus_Id, Double cus_dueAmount, Double cus_advAmount, Double cus_buyAmount) {
        this.cus_Id = cus_Id;
        this.cus_dueAmount = cus_dueAmount;
        this.cus_advAmount = cus_advAmount;
        this.cus_buyAmount = cus_buyAmount;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "cus_Id=" + cus_Id +
                ", cus_name='" + cus_name + '\'' +
                ", cus_shopName='" + cus_shopName + '\'' +
                ", cus_mobile='" + cus_mobile + '\'' +
                ", cus_email='" + cus_email + '\'' +
                ", cus_address='" + cus_address + '\'' +
                ", cus_dueAmount=" + cus_dueAmount +
                ", cus_advAmount=" + cus_advAmount +
                ", cus_buyAmount=" + cus_buyAmount +
                '}';
    }
}
