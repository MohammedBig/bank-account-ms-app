package org.example.accountservice;

import org.example.accountservice.clients.CustomerRestClient;
import org.example.accountservice.entities.BankAccount;
import org.example.accountservice.enums.AccountType;
import org.example.accountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        return args -> {
            customerRestClient.findAllCustomers().forEach(c->{
                bankAccountRepository.save(BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .balance((5000))
                        .createdAt(LocalDate.now())
                        .type(AccountType.CURRENT)
                        .currency("MAD")
                        .customerId(c.getId())
                        .build());
                bankAccountRepository.save(BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .balance((70000))
                        .createdAt(LocalDate.now())
                        .type(AccountType.CURRENT)
                        .currency("USD")
                        .customerId(c.getId())
                        .build());
            });

        };
    }
}
