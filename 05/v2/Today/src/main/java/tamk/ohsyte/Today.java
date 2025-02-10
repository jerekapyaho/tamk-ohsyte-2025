package tamk.ohsyte;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Today {
    public static void main(String[] args) {
        // Replace with a valid path to the events.csv file on your own computer!
        final String fileName = "/Users/jere/Projects/Java/tamk-ohsyte-2025/05/events.csv";
        EventProvider provider = new CSVEventProvider(fileName);

        final MonthDay monthDay = MonthDay.of(2, 10);

        // Get events for given day, any year, any category, newest first
        List<Event> events = provider.getEventsOfDate(monthDay);
        Collections.sort(events);
        Collections.reverse(events);
        
        for (Event event : events) {
            System.out.println(event);
        }
    }
}