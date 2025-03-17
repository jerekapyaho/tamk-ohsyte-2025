package tamk.ohsyte;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import tamk.ohsyte.commands.ListEvents;
import tamk.ohsyte.commands.ListProviders;
import tamk.ohsyte.datamodel.AnnualEvent;
import tamk.ohsyte.datamodel.AnnualEventComparator;
import tamk.ohsyte.datamodel.Event;
import tamk.ohsyte.datamodel.SingularEvent;
import tamk.ohsyte.datamodel.SingularEventComparator;
import tamk.ohsyte.providers.CSVEventProvider;

@Command(name = "today", subcommands = { ListProviders.class, ListEvents.class }, description = "Shows events from history and annual observations")
public class Today {
    public Today() {
        // Gets the singleton manager. Later calls to getInstance
        // will return the same reference.
        EventManager manager = EventManager.getInstance();

        // Construct a path to a file in the user's home directory,
        // in a subdirectory called ".today".
        String homeDirectory = System.getProperty("user.home");
        String configDirectory = ".today";
        Path path = Paths.get(homeDirectory, configDirectory, "events.csv");
        //System.out.println("Path = " + path.toString());

        // Create the events file if it doesn't exist
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.err.println("Unable to create events file");
                System.exit(1);
            }
        }

        String eventProviderId = "standard";

        // Add a CSV event provider that reads from the given file.
        manager.addEventProvider(
                new CSVEventProvider(path.toString(), eventProviderId));

        // Try to add an event provider with the same ID again:
        if (!manager.addEventProvider(
                new CSVEventProvider(path.toString(), eventProviderId))) {
            System.err.printf("Event provider '%s' is already registered%n", eventProviderId);
        }

        // TODO: Add SQLiteEventProvider
        
        List<Event> allEvents = EventManager.getInstance().getAllEvents();

        // Separate the events to lists by their types
        List<AnnualEvent> annualEvents = new ArrayList<>();
        List<SingularEvent> singularEvents = new ArrayList<>();
        for (Event event : allEvents) {
            if (event instanceof AnnualEvent) {
                annualEvents.add((AnnualEvent) event);
            } else if (event instanceof SingularEvent) {
                singularEvents.add((SingularEvent) event);
            }
        }

        // Just print the events (just like commands.ListEvents)
        if (!annualEvents.isEmpty()) {
            System.out.println("Observed today:");
            Collections.sort(annualEvents, new AnnualEventComparator());

            for (AnnualEvent a : annualEvents) {
                System.out.printf(
                        "- %s (%s) %n",
                        a.getDescription(),
                        a.getCategory());
            }
        }

        if (!singularEvents.isEmpty()) {
            System.out.println("---web".repeat(10));
            System.out.println("Today in history (events from web):");
            Collections.sort(singularEvents, new SingularEventComparator());
            Collections.reverse(singularEvents);

            for (SingularEvent s : singularEvents) {
                int year = s.getDate().getYear();

                System.out.printf(
                        "%d: %s (%s)%n",
                        year,
                        s.getDescription(),
                        s.getCategory());
            }

            System.out.println("---web".repeat(10));
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Today()).execute(args);
        System.exit(exitCode);
    }
}
