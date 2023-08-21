package com.nick.product.manage.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomersConfig {

    @Bean
    CommandLineRunner customerCommandLineRunner(CustomersRepository customersRepository){

        return args -> {
            Customer firstCustomer = new Customer("Nick",
                    "Nick General Strore",
                    "8757394648",
                    "nick@gmail.com",
                    "CheckPost Argada",
                    "SUP101");

            Customer secondCustomer = new Customer("Simran",
                    "Simran General Strore",
                    "8084605228",
                    "simran@gmail.com",
                    "GM Office, SIRKA",
                    "SUP102");

            Customer thirdCustomer = new Customer("Nick",
                    "Nicky General Strore",
                    "8757394647",
                    "nick@gmail.com",
                    "CheckPost Argada",
                    "SUP101");

            Customer forthCustomer = new Customer("Nick",
                    "Nicku General Strore",
                    "8757394646",
                    "nick@gmail.com",
                    "CheckPost Argada",
                    "SUP101");

            Customer fifthCustomer = new Customer("Nick",
                    "Simu General Strore",
                    "8757394645",
                    "nick@gmail.com",
                    "CheckPost Argada",
                    "SUP102");

            customersRepository.saveAll(
                    List.of(firstCustomer, secondCustomer, thirdCustomer, forthCustomer, fifthCustomer)
            );
        };

    }
}
