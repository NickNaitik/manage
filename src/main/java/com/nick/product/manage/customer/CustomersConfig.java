package com.nick.product.manage.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomersConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomersRepository customersRepository){

        return args -> {
            Customer firstCustomer = new Customer("Nick",
                    "Nick General Strore",
                    "8757394648",
                    "nick@gmail.com",
                    "CheckPost Argada");

            Customer secondCustomer = new Customer("Simran",
                    "Simran General Strore",
                    "8084605228",
                    "simran@gmail.com",
                    "GM Office, SIRKA");

            customersRepository.saveAll(
                    List.of(firstCustomer, secondCustomer)
            );
        };

    }
}
