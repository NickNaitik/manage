package com.nick.product.manage.Config;

import com.nick.product.manage.Entity.Product;
import com.nick.product.manage.Repository.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductsConfig {

    @Bean
    CommandLineRunner productCommandLineRunner(ProductsRepository productsRepository){

        return args -> {
            Product firstProduct = new Product(
                    "PEPSI 225ML",
                    "2250ML",
                    90L,
                    91.0,
                    85.0,
                    "SUP101");

            Product secondProduct = new Product(
                    "PEPSI 750ML",
                    "750ML",
                    240L,
                    37.3,
                    36.5,
                    "SUP101");

            Product thirdProduct = new Product(
                    "PEPSI 2000ML",
                    "2000ML",
                    240L,
                    91.0,
                    86.5,
                    "SUP101");

            Product forthProduct = new Product(
                    "7UP 750ML",
                    "750ML",
                    240L,
                    37.3,
                    36.5,
                    "SUP102");

            Product fifthProduct = new Product(
                    "DEW 750ML",
                    "750ML",
                    240L,
                    37.3,
                    36.5,
                    "SUP102");

            productsRepository.saveAll(
                    List.of(firstProduct, secondProduct, thirdProduct, forthProduct, fifthProduct)
            );
        };

    }
}
