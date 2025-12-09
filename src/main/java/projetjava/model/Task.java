package projetjava.model;

import java.time.LocalDate;

public class Task {

    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority; // "BASSE", "NORMALE", "HAUTE"
    private boolean completed;

    public Task(int id, String title, String description, LocalDate dueDate, String priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
    }

    // Constructeur pratique sans id (id = 0 par défaut)
    public Task(String title, String description, LocalDate dueDate, String priority, boolean completed) {
        this(0, title, description, dueDate, priority, completed);
    }

    // Optionnel : si on veut juste une tâche avec un titre
    public Task(String title) {
        this(0, title, "", null, "NORMALE", false);
    }

    // Getters / Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (priority != null && !priority.isBlank()) {
            sb.append("[").append(priority).append("] ");
        }

        sb.append(title != null ? title : "");

        if (dueDate != null) {
            sb.append(" (à faire pour ").append(dueDate).append(")");
        }

        if (completed) {
            sb.append(" ✔");
        }

        return sb.toString();
    }
}
