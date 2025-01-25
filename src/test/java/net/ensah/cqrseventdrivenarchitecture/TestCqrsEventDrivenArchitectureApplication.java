package net.ensah.cqrseventdrivenarchitecture;

import org.springframework.boot.SpringApplication;

public class TestCqrsEventDrivenArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.from(CqrsEventDrivenArchitectureApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
