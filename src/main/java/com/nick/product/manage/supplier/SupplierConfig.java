package com.nick.product.manage.supplier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SupplierConfig {

    @Bean
    CommandLineRunner supplierCommandLineRunner(SuppliersRepository suppliersRepository){

        return args -> {
            Supplier firstSupplier = new Supplier("Master_01",
                    "SUP101",
                    "First Supplier",
                    "firstsupplier@gmail.com",
                    "user",
                    "6687988767889");

            Supplier secondSupplier = new Supplier("Master_01",
                    "SUP101",
                    "Second Supplier",
                    "secondsupplier@gmail.com",
                    "user",
                    "797767578789");

            Supplier thirdSupplier = new Supplier("Master_01",
                    "SUP102",
                    "Third Supplier",
                    "thirdsupplier@gmail.com",
                    "user",
                    "7688977999");

            Supplier forthSupplier = new Supplier("Master_02",
                    "SUP102",
                    "Forth Supplier",
                    "forthsupplier@gmail.com",
                    "user",
                    "5679886897");

            Supplier fifthSupplier = new Supplier("Master_02",
                    "SUP102",
                    "Fifth Supplier",
                    "fifthsupplier@gmail.com",
                    "user",
                    "65787897658");

            suppliersRepository.saveAll(
                    List.of(firstSupplier, secondSupplier, thirdSupplier, forthSupplier, fifthSupplier)
            );
        };

    }
}
