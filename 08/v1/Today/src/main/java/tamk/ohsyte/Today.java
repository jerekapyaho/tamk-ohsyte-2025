package tamk.ohsyte;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import tamk.ohsyte.commands.ListProviders;
import tamk.ohsyte.commands.ListEvents;
import tamk.ohsyte.providers.CSVEventProvider;

@Command(name = "today", subcommands = { ListProviders.class, ListEvents.class }, description = "Shows events from history and annual observations")
public class Today {
    public Today() {
        // Gets the singleton manager. Later calls to getInstance
        // will return the same reference.
        EventManager manager = EventManager.getInstance();

        String fileName;

        // Add a CSV event provider that reads from the given file.
        // Replace with a valid path to the events.csv file on your own computer!
        fileName = "C:\\Users\\sdjeka\\.today\\events.csv";
        manager.addEventProvider(new CSVEventProvider(fileName, "Home directory"));

        fileName = "C:\\Users\\sdjeka\\Projects\\tamk-ohsyte-2025\\08\\singular-events.csv";
        manager.addEventProvider(new CSVEventProvider(fileName, "Repo testing"));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Today()).execute(args);
        System.exit(exitCode);
    }
}
