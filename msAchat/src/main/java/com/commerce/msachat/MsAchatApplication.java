package com.commerce.msachat;

import com.commerce.msachat.entities.Achat;
import com.commerce.msachat.repo.AchatRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MsAchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAchatApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(AchatRepo achatRepo) {
        return args -> {
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            ids.add(2L);
            Achat achat = Achat.builder()
                    .currency("MAD")
                    .date(LocalDate.of(2025,02,10))
                    .productsId(ids)
                    .total(100.02)
                    .build();
            achatRepo.save(achat);
        };
    }
}
