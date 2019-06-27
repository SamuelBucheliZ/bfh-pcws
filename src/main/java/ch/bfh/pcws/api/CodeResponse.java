package ch.bfh.pcws.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeResponse {

    private final String message;
    private final String code;

    public static CodeResponse fromError(String message) {
        String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return new CodeResponse(timestamp + ": " + message, null);
    }

    public static CodeResponse fromCode(String code) {
        return new CodeResponse(null, code);
    }

    private CodeResponse(
            String message,
            String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "CodeResponse{" +
                "message='" + message + '\'' +
                ", image='" + code + '\'' +
                '}';
    }
}
