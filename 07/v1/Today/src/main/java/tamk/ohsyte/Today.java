package tamk.ohsyte;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Today {
    public static void main(String[] args) {
        // Gets the singleton manager. Later calls to getInstance
        // will return the same reference.
        EventManager manager = EventManager.getInstance();

        // Add a CSV event provider that reads from the given file.
        // Replace with a valid path to the events.csv file on your own computer!
        String fileName = "/Users/jere/.today/events.csv";
        manager.addEventProvider(new CSVEventProvider(fileName));

        fileName = "/Users/jere/Projects/Java/tamk-ohsyte-2025/07/singular-events.csv";
        manager.addEventProvider(new CSVEventProvider(fileName));

        List<Event> allEvents = manager.getAllEvents();
        List<AnnualEvent> annualEvents = new ArrayList<>();
        List<SingularEvent> singularEvents = new ArrayList<>();
        for (Event event : allEvents) {
            if (event instanceof AnnualEvent) {
                annualEvents.add((AnnualEvent) event);
            } else if (event instanceof SingularEvent) {
                singularEvents.add((SingularEvent) event);
            }
        }

        System.out.println("Today:");
        Collections.sort(annualEvents);

        for (AnnualEvent a : annualEvents) {
            System.out.printf("- %s%n", a.getDescription());
        }
        //System.out.printf("%d events%n", annualEvents.size());

        System.out.println("\nToday in history:");
        Collections.sort(singularEvents);
        Collections.reverse(singularEvents);

        /*
        for (SingularEvent s : singularEvents) {
            System.out.printf("%d: %s%n", s.getDate().getYear(), s.getDescription());
        }
        */
        System.out.printf("%d events%n", singularEvents.size());
    }
}
