package pudding.toy.ourJourney.config;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    /**
     * Override default Spring Doc Object mapper provider and add the JsonNullableModule - otherwise the OpenAPI JSON
     * Spec interprets the JsonNullable wrong
     *
     * @param springDocConfigProperties given config properties in application yaml
     * @return provider for spring doc generation
     */
    @Bean
    public ObjectMapperProvider springdocObjectMapperProvider(SpringDocConfigProperties springDocConfigProperties) {
        ObjectMapperProvider objectMapperProvider = new ObjectMapperProvider(springDocConfigProperties);
        objectMapperProvider.jsonMapper().registerModule(jsonNullableModule());
        return objectMapperProvider;
    }

    /**
     * Register JsonNullableModule for openapi generation in springdoc (above) and for the default object mapper
     * used to serialize/deserialize JSON Payloads via web requests - otherwise the application is not able to mmap
     * JsonNullable<Type> fields
     *
     * @return json nullable module
     */
    @Bean
    public JsonNullableModule jsonNullableModule() {
        return new JsonNullableModule();
    }

}
