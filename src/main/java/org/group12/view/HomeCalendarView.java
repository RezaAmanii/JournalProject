package org.group12.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.group12.Observers.ICalenderObserver;
import org.group12.model.homeCalendar.CalendarActivityModel;

import java.time.ZonedDateTime;

/**
 * View class for the Home Calendar.
 */
public class HomeCalendarView implements ICalenderObserver {

    /**
     * Updates the view based on the changes in the calendar.
     */
    @Override
    public void update() {

    }

    /**
     * Configures the columns of the calendar activity box.
     *
     * @param calendarActivityBox The GridPane representing the calendar activity box.
     */
    public static void configureColumns(GridPane calendarActivityBox) {
        ColumnConstraints col1=new ColumnConstraints();
        col1.setPercentWidth(80);
        col1.setHgrow(Priority.SOMETIMES);

        ColumnConstraints col2=new ColumnConstraints();
        col2.setPercentWidth(20);
        col2.setHgrow(Priority.SOMETIMES);

        calendarActivityBox.getColumnConstraints().addAll(col1,col2);
    }

    /**
     * Configures a row in the calendar activity box.
     *
     * @param calendarActivityBox The GridPane representing the calendar activity box.
     */
    public static void configureRow(GridPane calendarActivityBox) {
        RowConstraints row=new RowConstraints();
        row.setVgrow(Priority.SOMETIMES);

        calendarActivityBox.getRowConstraints().add(row);
    }

    /**
     * Configures the style and margins of the calendar activity box.
     *
     * @param calendarActivityBox The GridPane representing the calendar activity box.
     */
    public static void configureStyleAndMargins(GridPane calendarActivityBox) {
        calendarActivityBox.setPadding(new Insets(5));
        calendarActivityBox.setStyle("-fx-background-color: #203649; -fx-background-radius: 10;");
        VBox.setMargin(calendarActivityBox, new Insets(5));
    }

    /**
     * Creates an ImageView for the delete button.
     *
     * @return The ImageView for the delete button.
     */
    public static ImageView createDeleteImageView() {
        ImageView imageView = new ImageView("/deleteWhite.png");
        imageView.setFitHeight(31.0);
        imageView.setFitWidth(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * Creates a Label with the text for a calendar activity.
     *
     * @param currCalendarActivityModel The current CalendarActivityModel.
     * @param activityTime             The ZonedDateTime representing the activity time.
     * @return The Label with the formatted text for the calendar activity.
     */
    public static Label createLabelText(CalendarActivityModel currCalendarActivityModel, ZonedDateTime activityTime) {
        Label text = new Label(currCalendarActivityModel.getTaskName() + ", " + activityTime.toLocalTime());
        text.setFont(Font.font("Bodoni MT Black", 15));
        text.setStyle("-fx-text-fill: white");
        return text;
    }

    /**
     * Creates a Label for the date in the calendar.
     *
     * @param currentDate The current date.
     * @return The Label for the date in the calendar.
     */
    public static Label createDateLabel(int currentDate) {
        Label date = new Label(String.valueOf(currentDate));
        date.setPadding(new Insets(5, 5, 5, 5));
        date.setFont(Font.font("Bodoni MT Black", 17));
        return date;
    }

    /**
     * Renders the activities indicator in the day box of the calendar.
     *
     * @param vDay The VBox representing the day box in the calendar.
     */
    public static void renderActivitiesIndicator(VBox vDay) {
        VBox notification = new VBox();
        notification.setPadding(new Insets(5, 5, 3, 5));
        notification.setStyle("-fx-background-color: #081e2a; -fx-background-radius: 10;");
        VBox.setMargin(notification, new Insets(2));
        vDay.getChildren().add(notification);
    }

}
