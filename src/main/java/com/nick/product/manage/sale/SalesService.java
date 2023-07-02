package com.nick.product.manage.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    private SalesRepository salesRepository;

    @Autowired
    public void SalesRepository(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sale> gatAllSales() {
        return salesRepository.findAll();
    }
}
