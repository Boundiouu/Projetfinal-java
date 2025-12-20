package projetjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Scene scene = new Scene(loader.load());

            // Ajout du style CSS
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            stage.setTitle("Gestionnaire de tâches");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("❌ Erreur lors du chargement de l'interface :");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
