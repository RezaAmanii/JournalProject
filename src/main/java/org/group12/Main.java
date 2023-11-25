package org.group12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.group12.Observers.IObservable;
import org.group12.Observers.alternative.IItemObserver;
import org.group12.controller.TodoController;
import org.group12.model.Container;
import org.group12.model.Items;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/mainMenu.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("PlanIT");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        Items items = new Items();
        Container container = new Container((IItemObserver) items);
        TodoController todoController = new TodoController(items);
    }
}