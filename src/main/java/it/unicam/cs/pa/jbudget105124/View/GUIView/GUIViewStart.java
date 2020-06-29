package it.unicam.cs.pa.jbudget105124.View.GUIView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di far partire lo stage della GUIView.
 */
public class GUIViewStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HomeInterface.fxml"));
        stage.setTitle("JBudget");
        stage.setScene(new Scene(root, 1050, 500));
        stage.show();
    }
}
