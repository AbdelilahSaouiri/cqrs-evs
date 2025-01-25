package net.ensah.shipementqueryservice;


import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShipementQueryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipementQueryServiceApplication.class, args);
    }

    @Bean
    CommandBus commandBus() {
        return SimpleCommandBus.builder().build();
    }




}
