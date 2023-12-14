package org.group12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.group12.model.dataHandler.SaveLoad;

public class Main extends Application{

    public  static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        newStart();
    }

    public static void newStart() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/group12/view/loginPage.fxml"));
        //Parent root = FXMLLoader.load(Main.class.getResource("/org/group12/view/mainMenu.fxml"));

        Scene scene = new Scene(root);

        mainStage.setTitle("PlanIT");
        mainStage.setScene(scene);
        mainStage.show();
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