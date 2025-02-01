package net.ensah.trackingcommandservice;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE;

@SpringBootApplication
public class TrackingCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackingCommandServiceApplication.class, args);
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
