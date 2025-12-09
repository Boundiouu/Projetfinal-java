package projetjava.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projetjava.model.Task;
import projetjava.model.TaskManager;
import projetjava.persistence.FileManager;

import java.time.LocalDate;
import java.util.List;

public class TaskController {

    private final TaskManager taskManager = new TaskManager();

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ChoiceBox<String> priorityChoiceBox;

    @FXML
    public void initialize() {
        // Priorités dispo
        ObservableList<String> priorities =
                FXCollections.observableArrayList("BASSE", "NORMALE", "HAUTE");
        priorityChoiceBox.setItems(priorities);
        priorityChoiceBox.setValue("NORMALE");

        // Chargement des tâches au démarrage
        List<Task> loadedTasks = FileManager.loadTasks();
        for (Task t : loadedTasks) {
            taskManager.addTask(t);
        }
        taskListView.getItems().addAll(taskManager.getTasks());
    }

    @FXML
    private void onAddTask() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        String priority = priorityChoiceBox.getValue();

        if (title == null || title.isBlank()) {
            // Pas de titre = on ne crée pas de tâche
            showAlert("Titre manquant", "Le titre de la tâche est obligatoire.");
            return;
        }

        if (priority == null || priority.isBlank()) {
            priority = "NORMALE";
        }

        Task task = new Task(title, description, dueDate, priority, false);
        taskManager.addTask(task);
        taskListView.getItems().add(task);

        // Reset des champs
        titleField.clear();
        descriptionField.clear();
        dueDatePicker.setValue(null);
        priorityChoiceBox.setValue("NORMALE");

        FileManager.saveTasks(taskManager.getTasks());
    }

    @FXML
    private void onDeleteTask() {
        Task selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Aucune tâche sélectionnée", "Veuillez choisir une tâche à supprimer.");
            return;
        }

        taskManager.removeTask(selected);
        taskListView.getItems().remove(selected);

        FileManager.saveTasks(taskManager.getTasks());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
