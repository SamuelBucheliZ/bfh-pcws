package ch.bfh.pcws.api;

public enum ServiceType {
    ECONOMY("E"), PREMIUM("P");

    private final String prefix;

    ServiceType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
