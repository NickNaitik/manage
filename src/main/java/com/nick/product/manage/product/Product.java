package com.nick.product.manage.product;

import javax.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1,
            initialValue = 101
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long prod_uid;
    private String prod_name;
    private String prod_quantity;
    private Long prod_avail;
    private Double prod_salesPrice;
    private Double prod_costPrice;
    private Long prod_sold = 0L;
    private String supplier_uid;

    public Product() {}

    public Product(String prod_name, String prod_quantity, Long prod_avail, Double prod_salesPrice, Double prod_costPrice, String supplier_uid) {
        this.prod_name = prod_name;
        this.prod_quantity = prod_quantity;
        this.prod_avail = prod_avail;
        this.prod_salesPrice = prod_salesPrice;
        this.prod_costPrice = prod_costPrice;
        this.supplier_uid = supplier_uid;
    }

    public Long getProd_uid() {
        return prod_uid;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_quantity() {
        return prod_quantity;
    }

    public void setProd_quantity(String prod_quantity) {
        this.prod_quantity = prod_quantity;
    }

    public Long getProd_avail() {
        return prod_avail;
    }

    public void setProd_avail(Long prod_avail) {
        this.prod_avail = prod_avail;
    }

    public Double getProd_salesPrice() {
        return prod_salesPrice;
    }

    public void setProd_salesPrice(Double prod_salesPrice) {
        this.prod_salesPrice = prod_salesPrice;
    }

    public Double getProd_costPrice() {
        return prod_costPrice;
    }

    public void setProd_costPrice(Double prod_costPrice) {
        this.prod_costPrice = prod_costPrice;
    }

    public Long getProd_sold() {
        return prod_sold;
    }

    public void setProd_sold(Long prod_sold) {
        this.prod_sold = prod_sold;
    }

    public String getSupplier_uid() {
        return supplier_uid;
    }

    public void setSupplier_uid(String supplier_uid) {
        this.supplier_uid = supplier_uid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prod_uid=" + prod_uid +
                ", prod_name='" + prod_name + '\'' +
                ", prod_quantity='" + prod_quantity + '\'' +
                ", prod_avail=" + prod_avail +
                ", prod_salesPrice=" + prod_salesPrice +
                ", prod_costPrice=" + prod_costPrice +
                ", prod_sold=" + prod_sold +
                '}';
    }
}
