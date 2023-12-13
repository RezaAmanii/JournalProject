package org.group12.controllerView;
//import org.group12.controllerView.Calendar.EventData;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.group12.controllerView.Calendar.EventData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Consumer;

import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.or;

public class NewEventWindowManager {

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

    public void initialize() {
        this.eventDate.setValue(LocalDate.now());
        initSpinners();
        setBindings();
    }

    private void initSpinners() {
        this.startHr.setValueFactory(hrFactory());
        this.endHr.setValueFactory(hrFactory());
        this.endHr.increment();
        this.startMin.setValueFactory(minFactory());
        this.endMin.setValueFactory(minFactory());
    }

    private static SpinnerValueFactory.IntegerSpinnerValueFactory minFactory() {
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, LocalTime.now().getMinute());
    }

    private static SpinnerValueFactory.IntegerSpinnerValueFactory hrFactory() {
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 23, LocalTime.now().getHour());
    }


    public void _initialize(Consumer<EventData> onSaveAction) {
        this.onSaveAction = onSaveAction;
        saveBtn.setOnMouseClicked(this::onSaveClk);
    }

    private void onSaveClk(MouseEvent msEvt) {
        var event = getEventData();
        this.onSaveAction.accept(event);
        ((Stage)root.getScene().getWindow()).close();
    }


    private void setBindings() {
        this.startTime = startTimeExpression();
        this.endTime = endTimeExpression();
        var startAfterEnd = Bindings.createBooleanBinding(() -> this.startTime.getValue().isAfter(this.endTime.getValue()), this.startTime, this.endTime);

        this.saveBtn.disableProperty()
                .bind(
                        or(startAfterEnd,
                                isEmpty(this.titleTxt.textProperty())));
    }

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

    private EventData getEventData() {
        return new EventData(titleTxt.getText(), descriptionTxt.getText(), startTime.getValue(), endTime.getValue());
    }


}