package me.stoliarov.anycipher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by Владислав on 23.09.2015.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "sample.fxml"
                )
        );

        final Parent root = (Parent) loader.load();
        final Controller controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Столяров В.Н. БПЗ1201.");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
