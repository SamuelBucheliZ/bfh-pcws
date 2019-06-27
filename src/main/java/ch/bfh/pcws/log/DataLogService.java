package ch.bfh.pcws.log;

import java.time.ZonedDateTime;

public interface DataLogService {

    void log(ZonedDateTime start, ZonedDateTime end, String accountName, String generatedBarcode);
}
