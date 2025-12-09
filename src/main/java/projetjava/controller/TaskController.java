package projetjava.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import projetjava.model.Task;
import projetjava.model.TaskManager;
import projetjava.persistence.FileManager;

import java.util.List;

public class TaskController {

    private final TaskManager taskManager = new TaskManager();

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private TextField titleField;

    @FXML
    public void initialize() {
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
        if (title == null || title.isBlank()) {
            return;
        }
        Task task = new Task(title);
        taskManager.addTask(task);
        taskListView.getItems().add(task);
        titleField.clear();

        FileManager.saveTasks(taskManager.getTasks());
    }

    @FXML
    private void onDeleteTask() {
        Task selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        taskManager.removeTask(selected);
        taskListView.getItems().remove(selected);

        FileManager.saveTasks(taskManager.getTasks());
    }
}
