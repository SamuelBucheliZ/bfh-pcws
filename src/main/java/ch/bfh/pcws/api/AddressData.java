package ch.bfh.pcws.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressData {

    private final String name;
    private final String address;
    private final String zipCode;
    private final String town;

    @JsonCreator
    public AddressData(
            @JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("zipCode") String zipCode,
            @JsonProperty("town") String town
    ) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getTown() {
        return town;
    }

    @Override
    public String toString() {
        return "AddressData{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }

}
