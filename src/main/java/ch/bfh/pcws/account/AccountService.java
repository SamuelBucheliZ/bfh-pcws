package ch.bfh.pcws.account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> getAccount(String accountName);

}
