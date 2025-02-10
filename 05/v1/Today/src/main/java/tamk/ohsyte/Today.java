package tamk.ohsyte;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Today {
    public static void main(String[] args) {
        EventProvider provider = new EmptyEventProvider();
        System.out.printf("Empty event provider has %d events%n",
                provider.getEvents().size());

        System.out.println("\nTrying simple event provider next:");
        provider = new SimpleEventProvider();

        final MonthDay monthDay = MonthDay.of(9, 25);

        // Get events for given day, any year, any category, newest first
        List<Event> events = provider.getEventsOfDate(monthDay);
        Collections.sort(events);
        Collections.reverse(events);
        
        for (Event event : events) {
            System.out.println(event);
        }
    }
}