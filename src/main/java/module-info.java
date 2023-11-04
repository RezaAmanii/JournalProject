module JournalProject {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.group12 to javafx.fxml;
    exports org.group12;

}