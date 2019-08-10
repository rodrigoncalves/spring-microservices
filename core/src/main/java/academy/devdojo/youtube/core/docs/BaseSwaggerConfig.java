package academy.devdojo.youtube.core.docs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@RequiredArgsConstructor
public class BaseSwaggerConfig {

    private final String basePackage;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Another Awesome course from DevDojo <3 SpringBoot Microservices")
                .description("Everybody is a Jedi by now")
                .version("1.0")
                .contact(new Contact("Rodrigo Gonçalves as the Jedi", "http://github.com/rodrigoncalves", "rodrigosg2000@gmail.com"))
                .build();
    }
}
