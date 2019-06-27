package ch.bfh.pcws;

import ch.bfh.pcws.code.CodeService;
import ch.bfh.pcws.image.CreateImageService;
import ch.bfh.pcws.log.DataLogService;
import ch.bfh.pcws.account.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CreateImageService createPrintCodeHandler(
            AccountService accountService,
            CodeService codeService,
            DataLogService dataLogService
    ) {
        return new CreateImageService(accountService, codeService, dataLogService);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
