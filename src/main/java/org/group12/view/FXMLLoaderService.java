package org.group12.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class FXMLLoaderService {
    public void loadFXML(AnchorPane root, Object controller, String fxmlFile) {
        try {
            URL url = getClass().getResource(fxmlFile);
            if (url == null) {
                System.out.println("Could not find file: " + fxmlFile);
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setRoot(root);
            fxmlLoader.setController(controller);
            System.out.println("Loading file: " + fxmlFile);
            fxmlLoader.load();
        } catch (IOException exception) {
            System.out.println("Could not load file: " + fxmlFile);
            exception.printStackTrace();
        }
    }

}
