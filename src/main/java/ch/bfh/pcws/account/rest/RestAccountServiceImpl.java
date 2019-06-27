package ch.bfh.pcws.account.rest;

import ch.bfh.pcws.account.Account;
import ch.bfh.pcws.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class RestAccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(RestAccountServiceImpl.class);

    private final PaketBlitzAccountServiceRestClient paketBlitzAccountServiceRestClient;

    public RestAccountServiceImpl(PaketBlitzAccountServiceRestClient paketBlitzAccountServiceRestClient) {
        this.paketBlitzAccountServiceRestClient = paketBlitzAccountServiceRestClient;
    }

    @Override
    public Optional<Account> getAccount(String accountName) {
        try {
            Account account = paketBlitzAccountServiceRestClient.getAccount(accountName);
            return Optional.of(account);
        } catch (Exception e) {
            logger.warn("Error while getting account {}", accountName, e);
            return Optional.empty();

        }
    }
}