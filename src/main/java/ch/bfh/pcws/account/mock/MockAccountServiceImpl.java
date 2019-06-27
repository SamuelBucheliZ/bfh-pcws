package ch.bfh.pcws.account.mock;

import ch.bfh.pcws.account.Account;
import ch.bfh.pcws.api.ServiceType;
import ch.bfh.pcws.account.AccountService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MockAccountServiceImpl implements AccountService {

    private static final String NO_PERMISSIONS_ACCOUNT_NAME = "noPermissions";
    private static final String ONLY_ECONOMY_PERMISSIONS_ACCOUNT_NAME = "onlyEconomy";
    private static final String ONLY_PREMIUM_PERMISSION_ACCOUNT_NAME = "onlyPremium";
    private static final String ALL_PERMISSIONS_ACCOUNT_NAME = "allPermissions";

    private static final List<Account> ACCOUNTS = Arrays.asList(
            new Account(1111, NO_PERMISSIONS_ACCOUNT_NAME, Collections.emptyList()),
            new Account(2222, ONLY_ECONOMY_PERMISSIONS_ACCOUNT_NAME, Collections.singletonList(ServiceType.ECONOMY)),
            new Account(3333, ONLY_PREMIUM_PERMISSION_ACCOUNT_NAME, Collections.singletonList(ServiceType.PREMIUM)),
            new Account(4444, ALL_PERMISSIONS_ACCOUNT_NAME, Arrays.asList(ServiceType.ECONOMY, ServiceType.PREMIUM))
    );

    @Override
    public Optional<Account> getAccount(String accountName) {
        return ACCOUNTS.stream()
                .filter(u -> u.getAccountName().equals(accountName))
                .findAny();
    }
}
