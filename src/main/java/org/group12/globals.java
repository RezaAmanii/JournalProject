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

public class globals {

    public static boolean[] toDoListsIDs=new boolean[1000];

    public static int createNewRandomID(boolean[] v) {
        Random random = new Random();
        int x = random.nextInt(v.length-1);
        while (v[x]) {
            x = random.nextInt(v.length-1);
        }
        v[x] = true;
        return x;
    }
    public static int createNewSeqID(boolean[] v) {
        for (int i=1;i<v.length;i++){
            if(!v[i]){
                v[i]=true;
                return i;
            }
        }
        return 0;
    }
    public static void showErrorAlert(String content){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void showConfirmationAlert(String content){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmed");
        alert.setContentText(content);
        alert.showAndWait();
    }


    public static void openNewForm(String formName,String title) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(globals.class.getResource(formName)));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    public static void spinnerTF(Spinner<Integer> spinner) {
        spinner.getEditor().setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));
    }
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
    public static String[] makeList(LinkedList linkedList) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        return strings;
    }
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
