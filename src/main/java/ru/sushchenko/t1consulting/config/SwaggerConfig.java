package ru.sushchenko.t1consulting.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
            title = "T1-Consulting Test",
            description = "Frequency API. Test task form T1-Consulting", version = "1.0.0",
            contact = @Contact(
                    name = "Sushchenko Artyom",
                    email = "artoymsushchenko@gmail.com",
                    url = "https://github.com/lordphiluren"
            )
    )
)
public class SwaggerConfig {
}
