module tamk.ohsyte.eventadder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.opencsv;
    requires com.fasterxml.jackson.databind;

    opens tamk.ohsyte.eventadder to javafx.fxml;
    exports tamk.ohsyte.eventadder;
}