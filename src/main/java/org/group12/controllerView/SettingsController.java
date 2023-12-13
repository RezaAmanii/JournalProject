package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.group12.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**

 *The SettingsController class handles the logic and actions related to the settings page.
 */

public class SettingsController implements Initializable {

    private static String homePage = "/org/group12/view/homePage.fxml";
    private static String toDoPage = "/org/group12/view/toDoPage.fxml";
    private static String calender = "/org/group12/view/calendarPage.fxml";
    private static String journal = "/org/group12/view/journalPage.fxml";
    private static String settings = "/org/group12/view/settingsPage.fxml";
    private static String mainMenue = "/org/group12/view/mainMenu.fxml";

    /**
     * Initializes the controller.
     *
     * @param url The location used to resolve relative paths for the root object.
     *@param resourceBundle The resources used to localize the root object.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void darkMode(MouseEvent mouseEvent) {
        changeWindosColor("my-1","my-2");
    }
    public void lightMode(MouseEvent mouseEvent) {
        changeWindosColor("my-2","my-1");

    }

    /**
     *Changes the window color by modifying the FXML files.
     *
     *@param color1 The color to be replaced.
     *@param color2 The color to replace with.
     */

    private void changeWindosColor(String color1,String color2)
    {
        try {

            List<String> fxmlPaths =List.of(homePage,toDoPage,calender,journal,settings,mainMenue);

            for(String filePath : fxmlPaths) {
                InputStream inputStream = getClass().getResourceAsStream(filePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder content = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();

                String modifiedContent = content.toString().replaceAll(color1, color2);

                // Write the modified content to a temporary file
                File tempFile = File.createTempFile("temp", ".fxml");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                writer.write(modifiedContent);
                writer.close();

                // Replace the original file with the modified content
                Path originalFilePath = Paths.get(getClass().getResource(filePath).toURI());
                Files.delete(originalFilePath);
                Files.move(tempFile.toPath(), originalFilePath);

            }

            Main.newStart();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


