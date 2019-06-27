package ch.bfh.pcws.log.mock;

import ch.bfh.pcws.log.DataLogEntry;
import ch.bfh.pcws.log.DataLogService;
import ch.bfh.pcws.util.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.ZonedDateTime;

public class MockDataLogServiceImpl implements DataLogService {

    private static final Logger logger = LoggerFactory.getLogger(MockDataLogServiceImpl.class);

    private final String applicationName;
    private final ObjectMapper objectMapper;

    public MockDataLogServiceImpl(String applicationName) {
        this.applicationName = applicationName;
        this.objectMapper = Json.createObjectMapper();
    }

    @Override
    public void log(ZonedDateTime start, ZonedDateTime end, String accountName, String generatedBarcode) {
        DataLogEntry dataLogEntry = new DataLogEntry(applicationName, start, end, accountName, generatedBarcode);
        try {
            String datalogEntryJson = objectMapper.writeValueAsString(dataLogEntry);
            logger.info("Generated datalog entry: {}", datalogEntryJson);
        } catch (IOException e) {
            logger.error("Could not generate datalog entry:", e);
        }
    }
}
