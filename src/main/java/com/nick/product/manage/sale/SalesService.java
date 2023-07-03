package com.nick.product.manage.sale;

import com.nick.product.manage.customer.CustomersService;
import com.nick.product.manage.product.Product;
import com.nick.product.manage.product.ProductsRepository;
import com.nick.product.manage.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesDto salesDto;

    private SalesRepository salesRepository;

    @Autowired
    private CustomersService customersService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private SaleValidator saleValidator;

    @Autowired
    public void SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sale> gatAllSales() {
        return salesRepository.findAll();
    }

    public ResponseEntity<String> addNewSale(SalesDto salesDto) {
        this.salesDto = salesDto;

        ResponseEntity<String> response = null;

        response = saleValidator.validateSale(salesDto);

        if(response.getStatusCode().is2xxSuccessful()) {

            String paymentStatus = "";

            if (salesDto.getSale_TotalAmount().equals(salesDto.getSale_PaymentRec())) {
                paymentStatus = "Full Payment";
                customersService.updateCustomer(salesDto.getSale_Cus_Id(), null, null, null, null, salesDto.getSale_TotalAmount());
            } else if (salesDto.getSale_PaymentRec() != 0 && salesDto.getSale_TotalAmount() > salesDto.getSale_PaymentRec()) {
                paymentStatus = "Partial Payment";
                customersService.updateCustomer(salesDto.getSale_Cus_Id(), null, null, salesDto.getSale_TotalAmount() - salesDto.getSale_PaymentRec(), null, salesDto.getSale_TotalAmount());
            } else if (salesDto.getSale_PaymentRec() == 0) {
                paymentStatus = "No Payment";
                customersService.updateCustomer(salesDto.getSale_Cus_Id(), null, null, salesDto.getSale_TotalAmount(), null, salesDto.getSale_TotalAmount());
            } else {
                throw new IllegalArgumentException("Updating Payment Status Failed!!");
            }

            Map<String, Integer> map = salesDto.getSale_Details();

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String productName = entry.getKey();
                Integer productQuantity = entry.getValue();

                Optional<Product> product = productsRepository.findProductByName(productName);

                productsService.updateProductAvabl(product.get().getProd_Id(), null, Long.valueOf(productQuantity));

            }

            Sale sale = new Sale(
                    salesDto.getSale_Cus_Id(),
                    salesDto.getSale_Details().toString(),
                    salesDto.getSale_TotalAmount(),
                    paymentStatus,
                    salesDto.getSale_PaymentRec()
            );

            Sale s = salesRepository.save(sale);

            response = ResponseEntity.accepted().body("Sale Done!, Your Sale id : " + s.getSale_Id());
        }

        return response;
    }
}
