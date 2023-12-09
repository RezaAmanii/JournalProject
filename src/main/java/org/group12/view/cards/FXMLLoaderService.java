package org.group12.view.cards;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FXMLLoaderService {
    public void loadFXML(AnchorPane root, Object controller, String fxmlFile) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(controller);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
