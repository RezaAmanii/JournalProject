package org.group12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

/**
 * Contains global utility methods and variables.
 */
public class globals {

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
    public static void openNewForm(String formName,String title,boolean resizable) throws IOException {
        Parent root= FXMLLoader.load(globals.class.getResource(formName));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setResizable(resizable);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
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

}