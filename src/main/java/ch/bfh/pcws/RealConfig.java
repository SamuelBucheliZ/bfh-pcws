package ch.bfh.pcws;

import ch.bfh.pcws.account.AccountService;
import ch.bfh.pcws.account.mock.MockAccountServiceImpl;
import ch.bfh.pcws.account.rest.PaketBlitzAccountServiceRestClient;
import ch.bfh.pcws.account.rest.RestAccountServiceImpl;
import ch.bfh.pcws.code.CodeService;
import ch.bfh.pcws.code.db.DbCodeServiceImpl;
import ch.bfh.pcws.code.mock.MockCodeServiceImpl;
import ch.bfh.pcws.log.DataLogService;
import ch.bfh.pcws.log.file.FileDataLogServiceImpl;
import ch.bfh.pcws.log.mock.MockDataLogServiceImpl;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("real")
public class RealConfig {

    @Bean
    public AccountService createAccountService(
            @Value("${pcws.accountService.useMock}") boolean useMock,
            @Value("${pcws.accountService.url}") String accountServiceUrl

    ) {
        if (useMock) {
            return new MockAccountServiceImpl();
        } else {
            PaketBlitzAccountServiceRestClient paketBlitzAccountServiceRestClient = Feign.builder()
                    .decoder(new JacksonDecoder())
                    .target(PaketBlitzAccountServiceRestClient.class, accountServiceUrl);
            return new RestAccountServiceImpl(paketBlitzAccountServiceRestClient);
        }
    }

    @Bean
    public CodeService createCodeService(
            @Value("${pcws.codeService.useMock}") boolean useMock,
            @Autowired DataSource dataSource
    ) {
        if (useMock) {
            return new MockCodeServiceImpl();
        } else {
            return new DbCodeServiceImpl(dataSource);
        }
    }

    @Bean
    public DataLogService createDataLogService(
            @Value("${pcws.datalogService.useMock}") boolean useMock,
            @Value("${pcws.datalogService.applicationName}") String applicationName,
            @Value("${pcws.datalogService.directory}") String datalogDirectory
    ) {
        if (useMock) {
            return new MockDataLogServiceImpl(applicationName);
        } else {
            return new FileDataLogServiceImpl(applicationName, datalogDirectory);
        }
    }

    @ConfigurationProperties(prefix = "pcws.datasource")
    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
}
