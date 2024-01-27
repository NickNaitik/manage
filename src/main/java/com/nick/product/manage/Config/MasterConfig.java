package com.nick.product.manage.Config;

import com.nick.product.manage.Entity.Master;
import com.nick.product.manage.Repository.MastersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MasterConfig {

    @Bean
    CommandLineRunner masterCommandLineRunner(MastersRepository mastersRepository){

        return args -> {
            Master firstMaster = new Master("MAS01",
                    "First Master",
                    "firstmaster@gmail.com",
                    "9162705815",
                    "admin",
                    0);

            Master secondMaster = new Master("MAS02",
                    "Second Master",
                    "secondmaster@gmail.com",
                    "9162705814",
                    "admin",
                    0);

            mastersRepository.saveAll(
                    List.of(firstMaster, secondMaster)
            );
        };

    }
}
