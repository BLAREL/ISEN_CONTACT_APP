import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class HelloFX extends Application {

    Stage primaryStage;
    @Override
    public void start(final Stage primaryStage) {
        try {
            // Localisation du fichier FXML.
            final URL url = getClass().getResource("/connect.fxml");
            // Création du loader.
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            // Chargement du FXML.
            final AnchorPane root = (AnchorPane) fxmlLoader.load();
            // Création de la scène.
            final Scene scene = new Scene(root, 800, 600);


            ConnectController connectController = (ConnectController) fxmlLoader.getController();
            connectController.setSecondScene("home.fxml");
            primaryStage.setScene(scene);
        } catch (IOException ex) {
            System.err.println("Erreur au chargement: " + ex);
        }
        primaryStage.setTitle("Test FXML");
        primaryStage.show();
    }
    @Override
    public void init() {
    }


    public static void main(String[] args) {
        launch();
    }

}
