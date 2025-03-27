package tamk.ohsyte.eventadder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.geometry.Insets;

import tamk.ohsyte.EventFactory;
import tamk.ohsyte.EventManager;
import tamk.ohsyte.datamodel.Event;
import tamk.ohsyte.providers.CSVEventProvider;
import tamk.ohsyte.providers.EventProvider;
import tamk.ohsyte.providers.SQLiteEventProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class App extends Application {
    private LocalDate eventDate;
    private String eventDescription;
    private String categoryText;
    private String eventProviderIdentifier = null;

    @Override
    public void start(Stage stage) throws IOException {
        final DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(e -> {
            eventDate = datePicker.getValue();
            System.err.println("Selected date: " + eventDate);
        });

        TextField descriptionTextField = new TextField();
        descriptionTextField.setPromptText("Event description: ");
        descriptionTextField.setOnAction(e -> eventDescription = descriptionTextField.getText());

        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Event category");
        categoryTextField.setOnAction(e -> categoryText = categoryTextField.getText());

        ComboBox<String> providerComboBox = new ComboBox<>();
        providerComboBox.getItems().addAll(EventManager.getInstance().getEventProviderIdentifiers());
        providerComboBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((o, oldValue, newValue) -> eventProviderIdentifier = newValue);

        var addButton = new Button("Add Event");
        addButton.setOnAction(event -> {
            Event newEvent = EventFactory.makeEvent(eventDate.toString(), eventDescription, categoryText);
            // TODO: actually add the event to the selected provider
            System.out.println("'Add Event' button was clicked, new event to add = " + event);
        });

        // TODO: Enable the Add button based on the selected event provider add support
        /*
        addButton.setEnabled(EventManager.getEventProvider(providerIdentifier).isAddSupported) or something
         */

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        vbox.getChildren().add(datePicker);
        vbox.getChildren().add(descriptionTextField);
        vbox.getChildren().add(categoryTextField);
        vbox.getChildren().add(providerComboBox);
        vbox.getChildren().add(addButton);

        Scene scene = new Scene(vbox, 320, 240);
        stage.setTitle("Event Adder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        EventManager manager = EventManager.getInstance();

        // Construct a path to a file in the user's home directory,
        // in a subdirectory called ".today".
        String homeDirectory = System.getProperty("user.home");
        String configDirectory = ".today";
        Path csvPath = Paths.get(homeDirectory, configDirectory, "events.csv");
        //System.out.println("Path = " + path.toString());

        // Create the events file if it doesn't exist
        if (!Files.exists(csvPath)) {
            try {
                Files.createFile(csvPath);
            } catch (IOException e) {
                System.err.println("Unable to create events file");
                System.exit(1);
            }
        }

        String eventProviderId = "standard";

        // Add a CSV event provider that reads from the given file.
        manager.addEventProvider(
                new CSVEventProvider(csvPath.toString(), eventProviderId));

        // Add an SQLite database event provider.
        Path databasePath = Paths.get(homeDirectory, configDirectory, "events.sqlite3");
        EventProvider sqliteEventProvider = new SQLiteEventProvider(databasePath.toString());
        manager.addEventProvider(sqliteEventProvider);

        launch();
    }
}