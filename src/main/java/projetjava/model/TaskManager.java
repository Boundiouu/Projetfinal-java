package projetjava.model;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (task == null || task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Le titre de la tâche est obligatoire.");
        }
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void updateTask(Task oldTask, Task newTask) {
        int index = tasks.indexOf(oldTask);
        if (index != -1) {
            tasks.set(index, newTask);
        }
    }

    public void clear() {
        tasks.clear();
    }
}
