package projetjava.persistence;

import projetjava.model.Task;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Nom du fichier de sauvegarde (dans le dossier du projet)
    private static final String FILE_NAME = "tasks.txt";

    public static void saveTasks(List<Task> tasks) {
        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            String line = encodeTask(task);
            lines.add(line);
        }

        try {
            Path path = Paths.get(FILE_NAME);
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des tâches : " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            // Pas de fichier = aucune tâche encore sauvegardée
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = decodeTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des tâches : " + e.getMessage());
        }

        return tasks;
    }

    // ---------- méthodes privées utilitaires ----------

    private static String encodeTask(Task task) {
        String title = escape(task.getTitle());
        String description = escape(task.getDescription());
        String date = task.getDueDate() != null ? task.getDueDate().toString() : "";
        String priority = escape(task.getPriority());
        String completed = task.isCompleted() ? "1" : "0";

        return title + "|" + description + "|" + date + "|" + priority + "|" + completed;
    }

    private static Task decodeTask(String line) {
        try {
            String[] parts = line.split("\\|", -1);

            String title = unescape(parts[0]);
            String description = unescape(parts[1]);
            String dateStr = parts[2];
            String priority = unescape(parts[3]);
            boolean completed = "1".equals(parts[4]);

            LocalDate date = dateStr.isBlank() ? null : LocalDate.parse(dateStr);

            // On recrée la tâche complète
            return new Task(title, description, date, priority, completed);

        } catch (Exception e) {
            System.err.println("Ligne invalide dans le fichier des tâches : " + line);
            return null;
        }
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.replace("|", "\\|");
    }

    private static String unescape(String value) {
        if (value == null) return "";
        return value.replace("\\|", "|");
    }
}
