package com.nick.product.manage.sale;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SalesDto {

    private Long sale_Cus_Id;
    private Map<String, Integer> sale_Details;
    private Double sale_TotalAmount;
    private Double sale_PaymentRec;

    public SalesDto() {}

    public SalesDto(Long sale_Cus_Id, Map<String, Integer> sale_Details, Double sale_TotalAmount, Double sale_PaymentRec) {
        this.sale_Cus_Id = sale_Cus_Id;
        this.sale_Details = sale_Details;
        this.sale_TotalAmount = sale_TotalAmount;
        this.sale_PaymentRec = sale_PaymentRec;
    }

    public Long getSale_Cus_Id() {
        return sale_Cus_Id;
    }

    public Map<String, Integer> getSale_Details() {
        return sale_Details;
    }

    public Double getSale_TotalAmount() {
        return sale_TotalAmount;
    }

    public Double getSale_PaymentRec() {
        return sale_PaymentRec;
    }

}
