package org.example.customerservice;

import org.example.customerservice.config.GlobalConfig;
import org.example.customerservice.entities.Customer;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null,"John", "Doe", "John@gmail.com"));
            customerRepository.save(new Customer(null,"Jane", "Doe", "Jane@gmail.com"));
            customerRepository.save(Customer.builder()
                            .id(null)
                            .firstName("simo")
                            .lastName("ostora")
                            .email("simo@gmail.com")
                    .build());
        };
    }

}
