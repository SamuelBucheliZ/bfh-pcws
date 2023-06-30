package ch.bfh.pcws;

import ch.bfh.pcws.account.AccountService;
import ch.bfh.pcws.code.CodeService;
import ch.bfh.pcws.image.CreateImageService;
import ch.bfh.pcws.log.DataLogService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
    public OpenAPI openApi() {
        return new OpenAPI() // see http://localhost:8080/swagger-ui/index.html
                .info(new Info().title("PrintCode WebService")
                        .description("Sample Project used in BFH AppTrans exercise, see https://github.com/SamuelBucheliZ/bfh-apptrans-exercise")
                        .version("v0.0.1"));
    }
}
