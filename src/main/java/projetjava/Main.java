package projetjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Vérifier le chemin du FXML
        URL fxmlUrl = Main.class.getResource("/fxml/main.fxml");
        if (fxmlUrl == null) {
            System.out.println("❌ ERREUR : main.fxml introuvable au chemin /fxml/main.fxml");
            return;
        } else {
            System.out.println("✅ FXML trouvé : " + fxmlUrl);
        }

        try {
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Scene scene = new Scene(loader.load());
            stage.setTitle("Gestionnaire de tâches");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("❌ Exception dans start() :");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
