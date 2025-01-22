import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class ShowDate {
    public static void main(String[] args) {
        TimeZone tz = TimeZone.getDefault();
        OffsetDateTime now = OffsetDateTime.now(tz.toZoneId());
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        String text = now.format(formatter);
        System.out.println(text);
    }
}
