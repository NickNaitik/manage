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
    private Long prod_Id;
    private String prod_Name;
    private String prod_Quantity;
    private Long prod_Available;
    private Double prod_SalesPrice;
    private Double prod_CostPrice;
    private Long prod_Sold = 0L;

    public Product() {}
    public Product(String prod_Name, String prod_Quantity, Long prod_Available, Double prod_SalesPrice, Double prod_CostPrice) {
        this.prod_Name = prod_Name;
        this.prod_Quantity = prod_Quantity;
        this.prod_Available = prod_Available;
        this.prod_SalesPrice = prod_SalesPrice;
        this.prod_CostPrice = prod_CostPrice;
    }

    public Long getProd_Id() {
        return prod_Id;
    }

    public String getProd_Name() {
        return prod_Name;
    }

    public void setProd_Name(String prod_Name) {
        this.prod_Name = prod_Name;
    }

    public String getProd_Quantity() {
        return prod_Quantity;
    }

    public void setProd_Quantity(String prod_Quantity) {
        this.prod_Quantity = prod_Quantity;
    }

    public Long getProd_Available() {
        return prod_Available;
    }

    public void setProd_Available(Long prod_Available) {
        this.prod_Available = prod_Available;
    }

    public Double getProd_SalesPrice() {
        return prod_SalesPrice;
    }

    public void setProd_SalesPrice(Double prod_SalesPrice) {
        this.prod_SalesPrice = prod_SalesPrice;
    }

    public Double getProd_CostPrice() {
        return prod_CostPrice;
    }

    public void setProd_CostPrice(Double prod_CostPrice) {
        this.prod_CostPrice = prod_CostPrice;
    }

    public Long getProd_Sold() {
        return prod_Sold;
    }

    public void setProd_Sold(Long prod_Sold) {
        this.prod_Sold = prod_Sold;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prod_Id=" + prod_Id +
                ", prod_Name='" + prod_Name + '\'' +
                ", prod_Quantity='" + prod_Quantity + '\'' +
                ", prod_Available=" + prod_Available +
                ", prod_SalesPrice=" + prod_SalesPrice +
                ", prod_CostPrice=" + prod_CostPrice +
                ", prod_Sold=" + prod_Sold +
                '}';
    }
}
