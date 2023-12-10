package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.group12.controllerView.MainMenuController;
import org.group12.model.User.UserModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    private UserModel user;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ProgressIndicator progressIndicator;


    @FXML
    private Label myLable;

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = new UserModel();
    }

    @FXML
    private void login() throws IOException {

        boolean state = user.checkCredentials(usernameField.getText(), passwordField.getText());
        if (state) {
            // open main page
            FXMLLoader loader = new FXMLLoader(MainMenuController.class.getResource("/org/group12/view/mainMenu.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage(); // Create a new stage
            newStage.setScene(new Scene(root));
            newStage.setTitle("Main Page");
            newStage.show();
        } else {
            myLable.setText("Invalid Email or Password !");
            myLable.setStyle("-fx-text-fill: red;");
        }
    }


}