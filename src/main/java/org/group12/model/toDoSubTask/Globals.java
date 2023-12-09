package org.group12.model.toDoSubTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * Contains global utility methods and variables.
 */
public class Globals {

    public static boolean[] toDoListsIDs=new boolean[1000];
    public static boolean[] toDoTasksIDs=new boolean[1000];

    public static boolean[] toDoSubTasksIDs=new boolean[1000];

    /**
     * Generates a new random ID not already present in the given array.
     *
     * @param v The array of IDs.
     * @return A new random ID.
     */
    public static int createNewRandomID(boolean[] v) {
        Random random = new Random();
        int x = random.nextInt(v.length-1);
        while (v[x]) {
            x = random.nextInt(v.length-1);
        }
        v[x] = true;
        return x;
    }

    /**
     * Generates a new sequential ID not already present in the given array.
     *
     * @param v The array of IDs.
     * @return A new sequential ID.
     */
    public static int createNewSeqID(boolean[] v) {
        for (int i=1;i<v.length;i++){
            if(!v[i]){
                v[i]=true;
                return i;
            }
        }
        return 0;
    }

    /**
     * Shows an error alert with the specified content.
     *
     * @param content The content of the error alert.
     */
    public static void showErrorAlert(String content){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Shows a confirmation alert with the specified content.
     *
     * @param content The content of the confirmation alert.
     */
    public static void showConfirmationAlert(String content){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmed");
        alert.setContentText(content);
        alert.showAndWait();
    }

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
     * Restricts the input of a Spinner control to numeric values only.
     *
     * @param spinner The Spinner control.
     */
    public static void spinnerTF(Spinner<Integer> spinner) {
        spinner.getEditor().setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));
    }

    /**
     * Populates a ListView control with items from a LinkedList.
     *
     * @param linkedList The LinkedList containing the items.
     * @param list       The ListView control.
     */
    public static void makeList(LinkedList linkedList, ListView<String> list) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        ObservableList<String>obs= FXCollections.observableArrayList(strings);
        list.setItems(obs);
    }

    /**
     * Converts a LinkedList to an array of Strings.
     *
     * @param linkedList The LinkedList to convert.
     * @return An array of Strings.
     */
    public static String[] makeList(LinkedList linkedList) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        return strings;
    }

    /**
     * Converts a LinkedList to an ObservableList of Strings.
     *
     * @param linkedList The LinkedList to convert.
     * @return An ObservableList of Strings.
     */
    public static ObservableList<String> makeObsList(LinkedList linkedList) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        return FXCollections.observableArrayList(strings);
    }

    public static void makeList(LinkedList linkedList, ChoiceBox<String> list) {

        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }

        ObservableList<String>obs= FXCollections.observableArrayList(strings);
        list.setItems(obs);
    }

    public static void defaultMakeList(LinkedList linkedList, ComboBox<String> list) {
        String[] strings = new String[linkedList.size()+1];
        strings[0]="-";
        int i = 1;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        ObservableList<String>obs= FXCollections.observableArrayList(strings);
        list.setItems(obs);
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
        private P root;
        private C controller;

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