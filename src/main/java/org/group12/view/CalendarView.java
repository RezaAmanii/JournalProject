package org.group12.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.group12.Observers.ICalenderObserver;
import org.group12.model.homeCalendar.CalendarActivity;

import java.time.ZonedDateTime;

public class CalendarView implements ICalenderObserver {

    @Override
    public void update() {

    }

    public static void configureColumns(GridPane calendarActivityBox) {
        ColumnConstraints col1=new ColumnConstraints();
        col1.setPercentWidth(80);
        col1.setHgrow(Priority.SOMETIMES);

        ColumnConstraints col2=new ColumnConstraints();
        col2.setPercentWidth(20);
        col2.setHgrow(Priority.SOMETIMES);

        calendarActivityBox.getColumnConstraints().addAll(col1,col2);
    }

    public static void configureRow(GridPane calendarActivityBox) {
        RowConstraints row=new RowConstraints();
        row.setVgrow(Priority.SOMETIMES);

        calendarActivityBox.getRowConstraints().add(row);
    }

    public static void configureStyleAndMargins(GridPane calendarActivityBox) {
        calendarActivityBox.setPadding(new Insets(5));
        calendarActivityBox.setStyle("-fx-background-color: #203649; -fx-background-radius: 10;");
        VBox.setMargin(calendarActivityBox, new Insets(5));
    }

    public static ImageView createDeleteImageView() {
        ImageView imageView = new ImageView("/deleteWhite.png");
        imageView.setFitHeight(31.0);
        imageView.setFitWidth(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static Label createLabelText(CalendarActivity currCalendarActivity, ZonedDateTime activityTime) {
        Label text = new Label(currCalendarActivity.getClientName() + ", " + activityTime.toLocalTime());
        text.setFont(Font.font("Bodoni MT Black", 15));
        text.setStyle("-fx-text-fill: white");
        return text;
    }

    public static Label createDateLabel(int currentDate) {
        Label date = new Label(String.valueOf(currentDate));
        date.setPadding(new Insets(5, 5, 5, 5));
        date.setFont(Font.font("Bodoni MT Black", 17));
        return date;
    }

    public static void renderActivitiesIndicator(VBox vDay) {
        VBox notification = new VBox();
        notification.setPadding(new Insets(5, 5, 3, 5));
        notification.setStyle("-fx-background-color: #081e2a; -fx-background-radius: 10;");
        VBox.setMargin(notification, new Insets(2));
        vDay.getChildren().add(notification);
    }




}
