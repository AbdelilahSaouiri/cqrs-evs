package net.ensah.shipementcommandservice;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE;

@SpringBootApplication
public class ShipmentCommandServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShipmentCommandServiceApplication.class, args);
    }

    @Bean
    CommandBus commandBus() {
        return SimpleCommandBus.builder().build();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer configureJackson() {

        return jackson2ObjectMapperBuilder -> {

            TypeResolverBuilder<?> typeResolver = new ObjectMapper.DefaultTypeResolverBuilder(OBJECT_AND_NON_CONCRETE);
            typeResolver = typeResolver.init(JsonTypeInfo.Id.CLASS, null);
            typeResolver = typeResolver.inclusion(JsonTypeInfo.As.WRAPPER_ARRAY);

            jackson2ObjectMapperBuilder.defaultTyping(typeResolver);
        };
    }
}
