package com.nick.product.manage.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductsConfig {

    @Bean
    CommandLineRunner commandLineProductRunner(ProductsRepository productsRepository){

        return args -> {
            Product firstProduct = new Product("PEPSI 225ML", "2250ML", 90L, 91.0,85.0);

            Product secondProduct = new Product("PEPSI 750ML","750ML",240L,37.3,36.5);

            productsRepository.saveAll(
                    List.of(firstProduct, secondProduct)
            );
        };

    }
}
