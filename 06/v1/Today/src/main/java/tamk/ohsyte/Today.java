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

        // Add a couple of event providers for testing purposes
        manager.addEventProvider(new FirstEventProvider("first"));
        manager.addEventProvider(new SecondEventProvider("second"));

        // Add a CSV event provider that reads from the given file.
        // Replace with a valid path to the events.csv file on your own computer!
        final String fileName = "/Users/jere/Projects/Java/tamk-ohsyte-2025/05/events.csv";
        manager.addEventProvider(new CSVEventProvider(fileName));

        List<Event> events = manager.getAllEvents();

        int providerCount = manager.getEventProviderCount();
        int eventCount = events.size();
        System.out.printf("Manager has %d event providers,%n", providerCount);
        System.out.printf("with a total of %d events.%n", eventCount);

        //
        // Get the event provider identifiers and print them.
        // This will result in an UnsupportedOperationException,
        // since the removal has not been implemented yet.
        //
        List<String> identifiers = manager.getEventProviderIdentifiers();
        System.out.println("Event providers: " 
            + Arrays.toString(identifiers.toArray()));

        //
        // Remove the CSV event provider, then get the counts again.
        // This will result in an UnsupportedOperationException,
        // since the removal has not been implemented yet.
        //
        manager.removeEventProvider("CSV");

        providerCount = manager.getEventProviderCount();
        events = manager.getAllEvents();  // refresh event list
        eventCount = events.size();
        System.out.printf("Manager has %d event providers,%n", providerCount);
        System.out.printf("with a total of %d events.%n", eventCount);
    }
}