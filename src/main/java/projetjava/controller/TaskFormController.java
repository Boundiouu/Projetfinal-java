package projetjava.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projetjava.model.Task;

public class TaskFormController {

    private Task task; // la tâche à modifier
    private boolean saved = false;

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ChoiceBox<String> priorityChoiceBox;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    public void initialize() {
        priorityChoiceBox.getItems().addAll("BASSE", "NORMALE", "HAUTE");
    }

    public void setTask(Task task) {
        this.task = task;

        titleField.setText(task.getTitle());
        descriptionField.setText(task.getDescription());
        dueDatePicker.setValue(task.getDueDate());
        priorityChoiceBox.setValue(task.getPriority());
        completedCheckBox.setSelected(task.isCompleted());
    }

    @FXML
    private void onSave() {

        task.setTitle(titleField.getText());
        task.setDescription(descriptionField.getText());
        task.setDueDate(dueDatePicker.getValue());
        task.setPriority(priorityChoiceBox.getValue());
        task.setCompleted(completedCheckBox.isSelected());

        saved = true;

        ((Stage) titleField.getScene().getWindow()).close();
    }

    @FXML
    private void onCancel() {
        ((Stage) titleField.getScene().getWindow()).close();
    }

    public boolean isSaved() {
        return saved;
    }
}
