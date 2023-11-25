package org.group12.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.group12.view.MainMenuController;

public class HomeController {

    private MainMenuController mainMenuController;

    // Components of the home screen
    @FXML
    private Button homeButton;
    @FXML
    private Button journalButton;
    @FXML
    private Button todoButton;
    @FXML
    private Button calendarButton;
    @FXML
    private Button settingsButton;
    @FXML
    private ImageView logo;




    // Component's method
    @FXML
    private void handleHomeButtonAction() {
        mainMenuController.switchToHomePane();
    }
    @FXML
    private void handleJournalButtonAction() {
        mainMenuController.switchToTabPane();
    }
    @FXML
    private void handleTodoButtonAction() {

    }
    @FXML
    private void handleCalendarButtonAction() {

    }
    @FXML
    private void handleSettingsButtonAction() {

    }


}
