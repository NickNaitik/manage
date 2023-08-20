package com.nick.product.manage.sale;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Sale {
    @Id
    @SequenceGenerator(
            name = "sale_sequence",
            sequenceName = "sale_sequence",
            allocationSize = 1,
            initialValue = 10001
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sale_sequence"
    )
    private Long sale_Id;
    private Long sale_Cus_Id;
    private String sale_Details;
    private Double sale_TotalAmount;
    private String sale_PaymentStatus;
    private Double sale_PaymentRec;
    private LocalDate sale_Date = LocalDate.now();

    private String supplier_uid;

    public Sale(){}

    public Sale(Long sale_Cus_Id, String sale_Details, Double sale_TotalAmount, String sale_PaymentStatus, Double sale_PaymentRec, String supplier_uid) {
        this.sale_Cus_Id = sale_Cus_Id;
        this.sale_Details = sale_Details;
        this.sale_TotalAmount = sale_TotalAmount;
        this.sale_PaymentStatus = sale_PaymentStatus;
        this.sale_PaymentRec = sale_PaymentRec;
        this.supplier_uid = supplier_uid;
    }

    public Long getSale_Cus_Id() {
        return sale_Cus_Id;
    }

    public Long getSale_Id() {
        return sale_Id;
    }

    public LocalDate getSale_Date() {
        return sale_Date;
    }

    public void setSale_Cus_Id(Long sale_Cus_Id) {
        this.sale_Cus_Id = sale_Cus_Id;
    }

    public String getSale_Details() {
        return sale_Details;
    }

    public void setSale_Details(String sale_Details) {
        this.sale_Details = sale_Details;
    }

    public Double getSale_TotalAmount() {
        return sale_TotalAmount;
    }

    public void setSale_TotalAmount(Double sale_TotalAmount) {
        this.sale_TotalAmount = sale_TotalAmount;
    }

    public String getSale_PaymentStatus() {
        return sale_PaymentStatus;
    }

    public void setSale_PaymentStatus(String sale_PaymentStatus) {
        this.sale_PaymentStatus = sale_PaymentStatus;
    }

    public Double getSale_PaymentRec() {
        return sale_PaymentRec;
    }

    public void setSale_PaymentRec(Double sale_PaymentRec) {
        this.sale_PaymentRec = sale_PaymentRec;
    }

    public String getSupplier_uid() {
        return supplier_uid;
    }

    public void setSupplier_uid(String supplier_uid) {
        this.supplier_uid = supplier_uid;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "sale_Id=" + sale_Id +
                ", sale_Cus_Id=" + sale_Cus_Id +
                ", sale_Details='" + sale_Details + '\'' +
                ", sale_TotalAmount=" + sale_TotalAmount +
                ", sale_PaymentStatus='" + sale_PaymentStatus + '\'' +
                ", sale_PaymentRec=" + sale_PaymentRec +
                ", sale_Date=" + sale_Date +
                '}';
    }

}