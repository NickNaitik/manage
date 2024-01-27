package com.nick.product.manage.Config;

import com.nick.product.manage.Entity.Supplier;
import com.nick.product.manage.Repository.SuppliersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SupplierConfig {

    @Bean
    CommandLineRunner supplierCommandLineRunner(SuppliersRepository suppliersRepository){

        return args -> {
            Supplier firstSupplier = new Supplier("MAS01",
                    "SUP101",
                    "First Supplier",
                    "firstsupplier@gmail.com",
                    "user1",
                    "6687988767889",
                    false);

            Supplier secondSupplier = new Supplier("MAS01",
                    "SUP102",
                    "Second Supplier",
                    "secondsupplier@gmail.com",
                    "user2",
                    "797767578789",
                    false);

            Supplier thirdSupplier = new Supplier("MAS01",
                    "SUP103",
                    "Third Supplier",
                    "thirdsupplier@gmail.com",
                    "user3",
                    "7688977999",
                    false);

            Supplier forthSupplier = new Supplier("MAS02",
                    "SUP104",
                    "Forth Supplier",
                    "forthsupplier@gmail.com",
                    "user4",
                    "5679886897",
                    false);

            Supplier fifthSupplier = new Supplier("MAS02",
                    "SUP105",
                    "Fifth Supplier",
                    "fifthsupplier@gmail.com",
                    "user",
                    "65787897658",
                    false);

            Supplier sixthSupplier = new Supplier();
            sixthSupplier.setSupplier_uid("SUP106");
            sixthSupplier.setSupplier_name("Sixth supplier");
            sixthSupplier.setSupplier_email("sixthsupplier@gmail.com");
            sixthSupplier.setSupplier_password("user6");
            sixthSupplier.setSupplier_mobile("9878767898");
            sixthSupplier.setTwoFactorEnabled(true);
            sixthSupplier.setMaster_uid("MAS01");

            Supplier seventhSupplier = new Supplier();
            seventhSupplier.setSupplier_uid("SUP107");
            seventhSupplier.setSupplier_name("Seventh supplier");
            seventhSupplier.setSupplier_email("seventhSupplier@gmail.com");
            seventhSupplier.setSupplier_password("user7");
            seventhSupplier.setSupplier_mobile("9878767899");
            seventhSupplier.setTwoFactorEnabled(true);
            seventhSupplier.setMaster_uid("MAS02");

            suppliersRepository.saveAll(
                    List.of(firstSupplier, secondSupplier, thirdSupplier, forthSupplier, fifthSupplier, sixthSupplier, seventhSupplier)
            );
        };

    }
}
