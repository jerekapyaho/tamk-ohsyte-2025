package tamk.ohsyte;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.*;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import tamk.ohsyte.commands.ListEvents;
import tamk.ohsyte.commands.ListProviders;
import tamk.ohsyte.datamodel.*;
import tamk.ohsyte.providers.CSVEventProvider;
import tamk.ohsyte.providers.EventProvider;
import tamk.ohsyte.providers.SQLiteEventProvider;

@Command(name = "today", subcommands = { ListProviders.class, ListEvents.class }, description = "Shows events from history and annual observations")
public class Today {
    public static final String LOGGER_NAME = "tamk.ohsyte.today";

    private static Logger logger = Logger.getLogger(LOGGER_NAME);

    public Today() {
        // Gets the singleton manager. Later calls to getInstance
        // will return the same reference.
        EventManager manager = EventManager.getInstance();

        // Construct a path to a file in the user's home directory,
        // in a subdirectory called ".today".
        String homeDirectory = System.getProperty("user.home");
        String configDirectory = ".today";
        Path configPath = Paths.get(homeDirectory, configDirectory);
        Path csvPath = Paths.get(homeDirectory, configDirectory, "events.csv");
        //System.out.println("Path = " + path.toString());
        try {
            // Create the config directory if it doesn't exist
            if (!Files.exists(configPath)) {
                Files.createDirectory(configPath);
                logger.info("Config directory " + configPath + " created");
            }

            Path logPath = Paths.get(homeDirectory, configDirectory, "today.log");
            Handler fh = new FileHandler(logPath.toString());
            Logger.getLogger(LOGGER_NAME).addHandler(fh);
            Logger.getLogger(LOGGER_NAME).setLevel(Level.FINEST);

            // Create the events file if it doesn't exist
            if (!Files.exists(csvPath)) {
                Files.createFile(csvPath);
            }
        } catch (IOException e) {
            final String message = "Unable to create config directory or events file, error = " + e.getLocalizedMessage();
            System.err.println(message);
            logger.warning(message);
            System.exit(1);
        }

        String eventProviderId = "standard";

        // Add a CSV event provider that reads from the given file.
        manager.addEventProvider(
                new CSVEventProvider(csvPath.toString(), eventProviderId));

        // Add an SQLite database event provider.
        Path databasePath = Paths.get(homeDirectory, configDirectory, "events.sqlite3");
        try {
            EventProvider sqliteEventProvider = new SQLiteEventProvider(databasePath);
            manager.addEventProvider(sqliteEventProvider);
        } catch (SQLException e) {
            final String message = "Error adding SQLite event provider: " + e.getLocalizedMessage();
            System.err.println(message);
            logger.warning(message);
            System.exit(1);
        }

        //System.out.println("TEST SQLiteEventProvider");
        //List<Event> sqliteEvents = sqliteEventProvider.getEventsOfDate(MonthDay.of(3, 24));
        //System.out.printf("Got %d events from SQLiteEventProvider%n", sqliteEvents.size());
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Today()).execute(args);
        System.exit(exitCode);
    }
}
