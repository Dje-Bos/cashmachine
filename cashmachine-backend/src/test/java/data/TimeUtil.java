package data;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static OffsetDateTime convert(String time) {
        return OffsetDateTime.from(ZonedDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault())));
    }
}
