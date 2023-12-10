package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;

public class LoginPageController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Disable the login button and show the progress indicator
        progressIndicator.setVisible(true);
        progressIndicator.setProgress(-1);

        // Create a background task to validate the username and password
        Task<Boolean> loginTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                // Simulate a delay to mimic the validation process
                Thread.sleep(2000);

                // Validate the username and password
                return username.equals("admin") && password.equals("password");
            }
        };

        // When the task is completed, enable the login button and hide the progress indicator
        loginTask.setOnSucceeded(event -> {
            boolean loginSuccessful = loginTask.getValue();

            if (loginSuccessful) {
                System.out.println("Login successful");
                // Perform actions after successful login
            } else {
                System.out.println("Login failed");

                // Show error message in a popup
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password. Please try again.");
                alert.showAndWait();
            }

            progressIndicator.setVisible(false);
            progressIndicator.setProgress(0);
        });

        // Start the login task in a background thread
        Thread loginThread = new Thread(loginTask);
        loginThread.setDaemon(true);
        loginThread.start();
    }

    @FXML
    private void checkCredentials() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // check the username and password
        if (username.equals("admin") && password.equals("password")) {
            System.out.println("Credentials are correct");
        } else {
            System.out.println("Credentials are incorrect");
        }
    }

}
