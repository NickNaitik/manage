package com.nick.product.manage.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesDto salesDto;

    private SalesRepository salesRepository;

    @Autowired
    public void SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sale> gatAllSales() {
        return salesRepository.findAll();
    }

    public String addNewSale(SalesDto salesDto) {
        this.salesDto = salesDto;

        Sale sale = new Sale(salesDto.getSale_Cus_Id(),
                salesDto.getSale_Details().toString(),
                salesDto.getSale_TotalAmount(),
                salesDto.getSale_PaymentStatus(),
                salesDto.getSale_PaymentRec()
        );
        Sale s = salesRepository.save(sale);
        return "Sale Done!, Your Sale id : " +s.getSale_Id();

    }
}
