package ch.bfh.pcws.log;

import java.time.ZonedDateTime;

public class DataLogEntry {

    private final String applicationName;
    private final ZonedDateTime start;
    private final ZonedDateTime end;
    private final String accountName;
    private final String generatedBarcode;

    public DataLogEntry(
            String applicationName,
            ZonedDateTime start,
            ZonedDateTime end,
            String accountName,
            String generatedBarcode
    ) {
        this.applicationName = applicationName;
        this.start = start;
        this.end = end;
        this.accountName = accountName;
        this.generatedBarcode = generatedBarcode;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getGeneratedBarcode() {
        return generatedBarcode;
    }
}
