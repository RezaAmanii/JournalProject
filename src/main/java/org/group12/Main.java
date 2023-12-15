package org.group12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/group12/view/loginPage.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("PlanIT");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                // Out commonted because it was causing errors with todoCollection not loading saved container
//                SaveLoad.getInstance().save();
            }
        }));

    }
}