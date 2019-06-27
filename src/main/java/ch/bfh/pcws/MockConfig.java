package ch.bfh.pcws;

import ch.bfh.pcws.code.CodeService;
import ch.bfh.pcws.code.mock.MockCodeServiceImpl;
import ch.bfh.pcws.log.DataLogService;
import ch.bfh.pcws.log.mock.MockDataLogServiceImpl;
import ch.bfh.pcws.account.mock.MockAccountServiceImpl;
import ch.bfh.pcws.account.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Profile("mock")
public class MockConfig {

    @Bean
    public AccountService createAccountService() {
        return new MockAccountServiceImpl();
    }

    @Bean
    public CodeService createCodeService() {
        return new MockCodeServiceImpl();
    }

    @Bean
    public DataLogService createDataLogService(
            @Value("${pcws.datalogService.applicationName}") String applicationName
    ) {
        return new MockDataLogServiceImpl(applicationName);
    }
}
