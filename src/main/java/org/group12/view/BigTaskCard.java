package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.group12.controller.BigTaskController;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import javafx.geometry.Insets;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BigTaskCard extends AnchorPane implements Initializable {

    // Class attributes
    private final String ID;
    private final ItemsSet items;

    // Controller
    private final BigTaskController bigTaskController = BigTaskController.getInstance();

    // FXML components
    @FXML
    private Label titleLabel;
    @FXML
    private Label dueDateLabel;
    @FXML
    private CheckBox statusCheckBox;
    @FXML
    private ImageView favouriteImageView;


    // Constructor
    public BigTaskCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bigTaskCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Make space between each cards
        double paddingValue = 10.0;
        VBox.setMargin(this, new Insets(paddingValue));

        update();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeFields();
        setupEventHandlers();
        update();
    }

    private void initializeFields(){
        this.titleLabel.setText(bigTaskController.getBigTaskTitle(this.ID));
        this.dueDateLabel.setText(bigTaskController.getBigTaskDueDate(this.ID));
        this.statusCheckBox.setSelected(bigTaskController.getBigTaskCheckBoxStatus(this.ID));

        if(favouriteImageView != null){
            this.favouriteImageView.setVisible(bigTaskController.getBigTaskFavouriteStatus(this.ID));
        } else{
            System.out.println("favouriteImageView is null");
        }
    }

    private void setupEventHandlers(){
        titleLabel.setOnMouseClicked(event -> {
            titleClicked();
        });

        statusCheckBox.setOnMouseClicked(event -> {
            checkBoxToggled();
        });
    }


    private void setDoubleClickEvent() {
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick();
            }
        });
    }

    @FXML
    public String cardClicked() {
        return this.ID;
    }

    @FXML
    private void imageViewClicked() {


    }

    @FXML
    public void titleClicked() {
        setDoubleClickEvent();
    }


    @FXML
    private void checkBoxToggled() {
        boolean isSelected = statusCheckBox.isSelected();
        bigTaskController.setBigTaskCheckBoxStatus(ID, isSelected);
    }

    public void update() {

        try{
            INameable item = items.getItem(this.ID);

            if(item instanceof IBigTask){
                IBigTask bigTask = (IBigTask) item;

                this.titleLabel.setText(bigTaskController.getBigTaskTitle(bigTask.getID()));
                this.dueDateLabel.setText(bigTaskController.getBigTaskDueDate(bigTask.getID()));
                this.statusCheckBox.setSelected(bigTaskController.getBigTaskCheckBoxStatus(bigTask.getID()));
                if(favouriteImageView != null){
                    this.favouriteImageView.setVisible(bigTaskController.getBigTaskFavouriteStatus(bigTask.getID()));
                } else{
                    System.out.println("favouriteImageView is null");
                }
            } else{
                System.out.println("Item with ID " + ID + " is not a IBigTask!");
            }
        } catch (ClassCastException error){
            System.out.println("Item with ID " + ID + " is not a IBigTask!");
        }
    }

    public String getID() {
        return ID;
    }


    private void handleDoubleClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Task");
        dialog.setHeaderText("Enter new name");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            bigTaskController.renameTheTask(this.ID, name);
        });
    }


}
