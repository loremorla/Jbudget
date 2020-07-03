package it.unicam.cs.pa.jbudget105124.View.GUIView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe che ha la responsabilità di far partire lo stage della GUIView.
 */
public class GUIViewStart extends Application {

    /**
     * Metodo da cui far partire lo stage delle GUI View
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HomeInterface.fxml"));
        stage.setTitle("JBudget");
        stage.setScene(new Scene(root, 1050, 500));
        stage.show();
    }
}
