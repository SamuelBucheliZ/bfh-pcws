package ch.bfh.pcws.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeRequest {

    private final String accountName;
    private final ServiceType serviceType;
    private final AddressData addressData;

    @JsonCreator
    public CodeRequest(
            @JsonProperty("accountName") String accountName,
            @JsonProperty("serviceType") ServiceType serviceType,
            @JsonProperty("addressData") AddressData addressData) {
        this.accountName = accountName;
        this.serviceType = serviceType;
        this.addressData = addressData;
    }

    public String getAccountName() {
        return accountName;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public AddressData getAddressData() {
        return addressData;
    }

    @Override
    public String toString() {
        return "CodeRequest{" +
                "accountName='" + accountName + '\'' +
                ", serviceType=" + serviceType +
                ", addressData=" + addressData +
                '}';
    }
}
