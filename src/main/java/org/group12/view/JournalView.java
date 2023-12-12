package org.group12.view;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import org.group12.Observers.IJournalObserver;

import java.net.URL;
import java.util.ResourceBundle;

public class JournalView  {

        public void update() {

        }

        public static Alert createConfirmationDialog() {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("Are you sure about clearing your journalEntry for today?");
                alert.setContentText("Choose your option.");

                return alert;
        }
}
