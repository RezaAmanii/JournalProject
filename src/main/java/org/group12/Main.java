package org.group12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.group12.Observers.IObservable;

public class Main extends Application{

    public  static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        newStart();
    }

    public static void newStart() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/org/group12/view/mainMenu.fxml"));

        Scene scene = new Scene(root);

        mainStage.setTitle("PlanIT");
        mainStage.setScene(scene);
        mainStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}