package ch.bfh.pcws.account.rest;

import ch.bfh.pcws.account.Account;
import feign.Param;
import feign.RequestLine;

public interface PaketBlitzAccountServiceRestClient {

    @RequestLine("GET /accounts/{accountName}")
    Account getAccount(@Param("accountName") String accountName);
}
