package org.group12.controllerView;

import org.group12.model.Calendar.EventData;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Consumer;

import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.or;


/**
 * This class represents the New Event view.
 * It allows users to create a new event by entering event details.
 */
public class NewEventView {

    @FXML
    private TextArea descriptionTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private DatePicker eventDate;

    @FXML
    private Spinner<Integer> endHr;

    @FXML
    private Spinner<Integer> endMin;

    @FXML
    private Spinner<Integer> startHr;

    @FXML
    private Spinner<Integer> startMin;

    @FXML
    private TextField titleTxt;

    @FXML
    private BorderPane root;

    private Consumer<EventData> onSaveAction;

    private ObservableValue<LocalDateTime> startTime;
    private ObservableValue<LocalDateTime> endTime;

    /**
     * Initializes the New Event view.
     * Sets the current date as the default event date and initializes the spinners.
     */
    public void initialize() {
        this.eventDate.setValue(LocalDate.now());
        initSpinners();
        setBindings();
    }

    /**
     * Initializes the spinners for selecting start and end hours and minutes.
     * Sets the spinner value factories for hours and minutes with default values based on the current time.
     */
    private void initSpinners() {
        this.startHr.setValueFactory(hrFactory());
        this.endHr.setValueFactory(hrFactory());
        this.endHr.increment();
        this.startMin.setValueFactory(minFactory());
        this.endMin.setValueFactory(minFactory());
    }


    /**
     * Creates a spinner value factory for minutes.
     * The factory creates IntegerSpinnerValueFactory with a range from 0 to 59,
     * and the initial value is set to the current minute of the system time.
     *
     * @return The spinner value factory for minutes.
     */
    private static SpinnerValueFactory.IntegerSpinnerValueFactory minFactory() {
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, LocalTime.now().getMinute());
    }

    private static SpinnerValueFactory.IntegerSpinnerValueFactory hrFactory() {
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 23, LocalTime.now().getHour());
    }


    /**
     * Initializes the New Event view with the save action.
     *
     * @param onSaveAction The action to be performed when the event is saved.
     */
    public void _initialize(Consumer<EventData> onSaveAction) {
        this.onSaveAction = onSaveAction;
        saveBtn.setOnMouseClicked(this::onSaveClk);
    }

    /**
     * Event handler for the save button click event.
     *
     * @param msEvt The mouse event.
     */
    private void onSaveClk(MouseEvent msEvt) {
        var event = getEventData();
        this.onSaveAction.accept(event);
        ((Stage)root.getScene().getWindow()).close();
    }


    /**
     * Sets the bindings for the save button disable property based on the event start and end times and title text.
     * The save button will be disabled if the start time is after the end time or if the title text is empty.
     */
    private void setBindings() {
        this.startTime = startTimeExpression();
        this.endTime = endTimeExpression();
        var startAfterEnd = Bindings.createBooleanBinding(() -> this.startTime.getValue().isAfter(this.endTime.getValue()), this.startTime, this.endTime);

        this.saveBtn.disableProperty()
                .bind(
                        or(startAfterEnd,
                                isEmpty(this.titleTxt.textProperty())));
    }


    /**
     * Creates an expression for the end time of the event.
     *
     * @return The observable value representing the end time of the event.
     */
    private ObservableValue<LocalDateTime> endTimeExpression() {
        return endHr.valueProperty()
                .flatMap(hr -> endMin.valueProperty().map(min -> LocalTime.of(hr, min)))
                .flatMap(time -> eventDate.valueProperty().map(time::atDate));
    }

    private ObservableValue<LocalDateTime> startTimeExpression() {
        return startHr.valueProperty()
                .flatMap(hr -> startMin.valueProperty().map(min -> LocalTime.of(hr, min)))
                .flatMap(time -> eventDate.valueProperty().map(time::atDate));
    }


    /**
     * Retrieves the event data from the input fields.
     *
     * @return The event data.
     */
    private EventData getEventData() {
        return new EventData(titleTxt.getText(), descriptionTxt.getText(), startTime.getValue(), endTime.getValue());
    }


}