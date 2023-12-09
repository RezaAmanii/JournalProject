package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.group12.controller.BigTaskController;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import javafx.geometry.Insets;


import java.io.IOException;

public class BigTaskCard extends AnchorPane {

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

    @FXML
    private void initialize(){
        this.titleLabel.setText(bigTaskController.getBigTaskTitle(this.ID));
        this.dueDateLabel.setText(bigTaskController.getBigTaskDueDate(this.ID));
        this.statusCheckBox.setSelected(bigTaskController.getBigTaskCheckBoxStatus(this.ID));

        if(favouriteImageView != null){
            this.favouriteImageView.setVisible(bigTaskController.getBigTaskFavouriteStatus(this.ID));
        } else{
            System.out.println("favouriteImageView is null");
        }
    }

    @FXML
    private void cardClicked() {

    }

    @FXML
    private void imageViewClicked() {

    }

    // TODO: method name might be changed to better fit the actual way the title is changed
    @FXML
    private void titleClicked() {
        // here should be the code to get the new title
        //String newTitle =
        //taskListController.changeListTitle(newTitle, ID);
    }

    // TODO: hur funkar taskController?
    @FXML
    private void checkBoxToggled() {
        boolean isSelected = statusCheckBox.isSelected();
        //taskController.handleSetStatus(ID, isSelected);
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
}
