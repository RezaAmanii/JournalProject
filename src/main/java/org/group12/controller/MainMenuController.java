package org.group12.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    public VBox sideBar;
    public Pane homePane;
    public Label homeLBL;
    public Pane journalPane;
    public Label journalLBL;
    public Pane todoPane;
    public Label todoLBL;
    public Pane calendarPane;
    public Label calendarLBL;
    public Pane settingsPane;
    public Label settingsLBL;
    public Label nameLBL;
    public Label homeLBL1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean sideBarExpanded=false;
    public void sideBarOnMouseEnter(){
        sideBarExpanded=!sideBarExpanded;
        if (sideBarExpanded){
            sideBar.setPrefWidth(sideBar.getMaxWidth());

            calendarLBL.setVisible(true);
            settingsLBL.setVisible(true);
            homeLBL.setVisible(true);
            todoLBL.setVisible(true);
            journalLBL.setVisible(true);
            homeLBL1.setVisible(true);

        }
        else{
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
