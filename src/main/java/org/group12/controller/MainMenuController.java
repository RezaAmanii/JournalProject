package org.group12.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private Pane calendarBTN;

    @FXML
    private Label calendarLBL;

    @FXML
    private Pane homeBtn;

    @FXML
    private Label homeLBL;

    @FXML
    private Label homeLBL1;

    @FXML
    private Label journalLBL;

    @FXML
    private Pane journalPane;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private BorderPane mainWindowBorder;

    @FXML
    private ImageView menuImg;

    @FXML
    private ImageView menuImg211;

    @FXML
    private ImageView menuImg2111;

    @FXML
    private ImageView menuImg2112;

    @FXML
    private ImageView menuImg2113;

    @FXML
    private ImageView menuImg2114;

    @FXML
    private Label settingsLBL;

    @FXML
    private Pane settingsPane;

    @FXML
    private VBox sideBar;

    @FXML
    private Pane todoBTN;

    @FXML
    private Label todoLBL;


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
        loadPage("/org/group12/view/homePage.fxml");
    }
    public void switchToTabPane() {
        loadPage("/org/group12/view/toDoPage.fxml");
    }
    public void switchToHomePane() {
        loadPage("/org/group12/view/homePage.fxml");

    }
    public void switchToCalendarPane() {loadPage("/org/group12/view/calendarPage.fxml");}
    public void switchToJournalPane() {loadPage("/org/group12/view/journalPage.fxml");}
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
