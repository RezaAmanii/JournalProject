package org.group12;

import org.group12.view.MainMenuController;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SubTasksController implements Initializable {


    public Label taskNameLBL;
    public VBox subTasksPane;
    public AnchorPane deletePane;
    public ImageView deleteImg;
    public TextField deadlineTF;
    public AnchorPane addPane;
    public ImageView addImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskNameLBL.setText(MainMenuController.selectedTask.getTaskName());
        deadlineTF.setText(MainMenuController.selectedTask.getTaskDeadline().toString());
    }
}
