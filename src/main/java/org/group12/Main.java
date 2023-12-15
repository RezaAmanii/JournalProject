package org.group12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.group12.model.dataHandler.SaveLoad;

import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application{

    public  static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        newStart(true);
    }

    public static void newStart(boolean state) throws Exception {
        List<Window> openStages = Stage.getWindows().stream()
                .filter(window -> window instanceof Stage)
                .collect(Collectors.toList());

        // Close all open stages
        openStages.forEach(window -> ((Stage) window).close());
        // Create a new stage and load the appropriate FXML based on the state
        Parent root = null;
        if (state) {
            root = FXMLLoader.load(Main.class.getResource("/org/group12/view/loginPage.fxml"));
        } else {
            root = FXMLLoader.load(Main.class.getResource("/org/group12/view/mainMenu.fxml"));
        }

        Scene scene = new Scene(root);

        Stage newStage = new Stage();
        newStage.setTitle("PlanIT");
        newStage.setScene(scene);
        newStage.show();
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