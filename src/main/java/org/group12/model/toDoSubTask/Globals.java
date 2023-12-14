package org.group12.model.toDoSubTask;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Globals {

    /**
     * Opens a new form.
     *
     * @param formName  The name of the form file.
     * @param title     The title of the form.
     * @param resizable Indicates if the form should be resizable.
     * @throws IOException If an I/O error occurs while loading the form.
     */
    public static <T> T openNewForm(String formName,String title,boolean resizable) throws IOException {
        var loader = new FXMLLoader(Globals.class.getResource(formName));
        Parent root= loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setResizable(resizable);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }


    /**
     * Loads an FXML file and creates an FXML UI object.
     *
     * @param fxmlUrl The URL of the FXML file to load.
     * @param <P>     The type of the root element in the FXML file.
     * @param <C>     The type of the controller associated with the FXML file.
     * @return An FxmlUi object representing the loaded FXML file.
     * @throws RuntimeException if an error occurs while loading the FXML file.
     */
    public static <P extends Parent,C> FxmlUi<P,C> loadFxml(String fxmlUrl) {
        var loader = new FXMLLoader(Globals.class.getResource(fxmlUrl));
        try {
            P root = loader.load();
            return new FxmlUi<>(root, loader.getController());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Represents an FXML UI object containing the root element and the associated controller.
     *
     * @param <P> The type of the root element.
     * @param <C> The type of the controller.
     */
    public static class FxmlUi<P extends Parent,C> {
        private final P root;
        private final C controller;

        /**
         * Creates an FxmlUi object with the specified root element and controller.
         *
         * @param root      The root element of the FXML UI.
         * @param controller The controller associated with the FXML UI.
         */
        public FxmlUi(P root, C controller) {
            this.root = root;
            this.controller = controller;
        }

        /**
         * Retrieves the controller associated with the FXML UI.
         *
         * @return The controller associated with the FXML UI.
         */
        public C getController() {
            return controller;
        }

        /**
         * Retrieves the root element of the FXML UI.
         *
         * @return The root element of the FXML UI.
         */
        public P getRoot() {
            return root;
        }
    }

}