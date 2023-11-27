module JournalProject {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.group12 to javafx.fxml;
    exports org.group12;
    exports org.group12.model;
    opens org.group12.model to javafx.fxml;
    exports org.group12.controller;
    opens org.group12.controller to javafx.fxml;
    exports org.group12.view;
    opens org.group12.view to javafx.fxml;
    exports org.group12.model.Calendar;
    opens org.group12.model.Calendar to javafx.fxml;
    exports org.group12.model.todo;
    opens org.group12.model.todo to javafx.fxml;
    exports org.group12.model.journal;
    opens org.group12.model.journal to javafx.fxml;
    exports org.group12.model.todo.factories;
    opens org.group12.model.todo.factories to javafx.fxml;
    exports org.group12.util;
    opens org.group12.util to javafx.fxml;
    exports org.group12.controllerView;
    opens org.group12.controllerView to javafx.fxml;

}