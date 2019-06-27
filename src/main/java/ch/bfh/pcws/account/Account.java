package ch.bfh.pcws.account;

import ch.bfh.pcws.api.ServiceType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    private final int accountId;
    private final String accountName;
    private final List<ServiceType> supportedServiceTypes;

    @JsonCreator
    public Account(
            @JsonProperty("accountId") int accountId,
            @JsonProperty("accountName") String accountName,
            @JsonProperty("supportedServiceTypes") List<ServiceType> supportedServiceTypes
    ) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.supportedServiceTypes = supportedServiceTypes;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public List<ServiceType> getSupportedServiceTypes() {
        return supportedServiceTypes;
    }
}
