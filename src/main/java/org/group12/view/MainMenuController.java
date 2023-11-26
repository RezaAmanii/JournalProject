package org.group12.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * The controller class for the main menu view.
 */
public class MainMenuController implements Initializable {
    public VBox sideBar;
    public Label homeLBL;
    public Pane journalPane;
    public Label journalLBL;
    public Label todoLBL;
    public Label calendarLBL;
    public Pane settingsPane;
    public Label settingsLBL;
    public Label homeLBL1;
    public BorderPane mainWindowBorder;
    public StackPane mainStackPane;

    /**
     * Loads the specified FXML page and sets it as the content of the main stack pane.
     *
     * @param pageFXML the path to the FXML file representing the page
     */
    private void loadPage(String pageFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageFXML));
            Parent page = loader.load();

            mainStackPane.getChildren().clear();
            mainStackPane.getChildren().add(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Loads the home page by default.
     *
     * @param url            the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loadPage("homePage.fxml");
    }
    public void switchToTabPane() {
        loadPage("toDoPage.fxml");
    }
    public void switchToHomePane() {
        loadPage("homePage.fxml");

    }
    public void switchToCalendarPane() {loadPage("calendarPage.fxml");}
    public void switchToJournalPane() {loadPage("journalPage.fxml");}
    public boolean sideBarExpanded = false;

    /**
     * Toggles the visibility of the sidebar and its labels when the mouse enters or exits the sidebar area.
     */
    public void sideBarOnMouseEnter() {
        sideBarExpanded = !sideBarExpanded;
        if (sideBarExpanded) {
            sideBar.setPrefWidth(sideBar.getMaxWidth());

            calendarLBL.setVisible(true);
            settingsLBL.setVisible(true);
            homeLBL.setVisible(true);
            todoLBL.setVisible(true);
            journalLBL.setVisible(true);
            homeLBL1.setVisible(true);

        } else {
            sideBar.setPrefWidth(sideBar.getMinWidth());
            calendarLBL.setVisible(false);
            settingsLBL.setVisible(false);
            homeLBL.setVisible(false);
            todoLBL.setVisible(false);
            journalLBL.setVisible(false);
            homeLBL1.setVisible(false);
        }

    }

}
