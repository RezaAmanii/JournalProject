package org.group12.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.group12.Observers.ITaskObserver;
import org.group12.model.toDoSubTask.Globals;

import java.io.IOException;
import java.util.Objects;

public class TaskView  {

    public static void openNewForm(String formName,String title,boolean resizable) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(Globals.class.getResource(formName)));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setResizable(resizable);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
