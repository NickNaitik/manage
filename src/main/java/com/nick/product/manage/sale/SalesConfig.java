package com.nick.product.manage.sale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SalesConfig {

    @Bean
    CommandLineRunner saleCommandLine(SalesRepository salesRepository) {

        Map<Long, Integer> soldProduct = new HashMap<Long, Integer>();
        soldProduct.put(101L, 10);
        soldProduct.put(102L, 12);

        return args -> {

            Sale sale1 = new Sale(1001L,
                    soldProduct.toString(),
                    2000.0,
                    "Partial",
                    1200.0);

            Sale sale2 = new Sale(1001L,
                    soldProduct.toString(),
                    2000.0,
                    "Completed",
                    2000.0);


            salesRepository.saveAll(
                    List.of(sale1, sale2)
            );
        };
    }
}
