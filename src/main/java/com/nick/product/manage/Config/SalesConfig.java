package com.nick.product.manage.Config;

import com.nick.product.manage.Entity.Sale;
import com.nick.product.manage.Repository.SalesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SalesConfig {

    @Bean
    CommandLineRunner saleCommandLineRunner(SalesRepository salesRepository) {

        Map<Long, Integer> soldProduct = new HashMap<Long, Integer>();
        soldProduct.put(101L, 10);
        soldProduct.put(102L, 12);

        return args -> {

            Sale sale1 = new Sale(1001L,
                    soldProduct.toString(),
                    2000.0,
                    "Partial",
                    1200.0,
                    "SUP101");

            Sale sale2 = new Sale(1002L,
                    soldProduct.toString(),
                    2000.0,
                    "Completed",
                    2000.0,
                    "SUP102");


            salesRepository.saveAll(
                    List.of(sale1, sale2)
            );
        };
    }
}
