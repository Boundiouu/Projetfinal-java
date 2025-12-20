package projetjava.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
                FXCollections.observableArrayList("BASSE", "NORMALE", "NORMALE", "HAUTE");
        priorityChoiceBox.setItems(priorities);
        priorityChoiceBox.setValue("NORMALE");

        // Message quand la liste est vide
        taskListView.setPlaceholder(new Label("Aucune tâche pour le moment"));

        // Chargement des tâches au démarrage
        List<Task> loadedTasks = FileManager.loadTasks();
        for (Task t : loadedTasks) {
            taskManager.addTask(t);
        }
        taskListView.getItems().addAll(taskManager.getTasks());

        // Double-clic sur une tâche = ouvrir la fenêtre de modification
        taskListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                onEditTask();
            }
        });
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

    @FXML
    private void onEditTask() {
        Task selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Aucune tâche sélectionnée", "Veuillez choisir une tâche à modifier.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/taskForm.fxml"));
            Parent root = loader.load();

            TaskFormController formController = loader.getController();
            formController.setTask(selected);

            Stage stage = new Stage();
            stage.setTitle("Modifier la tâche");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if (formController.isSaved()) {
                taskListView.refresh();
                FileManager.saveTasks(taskManager.getTasks());
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur est survenue lors de l'édition de la tâche.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
