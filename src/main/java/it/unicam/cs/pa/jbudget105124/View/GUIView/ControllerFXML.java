package it.unicam.cs.pa.jbudget105124.View.GUIView;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Interfaccia che ha il compito di fare da controller alla GUI View e di interagire
 * con il controller dell'MVC.
 */
public interface ControllerFXML extends Initializable {

    /**
     * Metodo per aprire una nuova finestra
     * @param title
     * @param fileFXML
     * @param controllerFXML
     */
    default void openWindow(String title, String fileFXML,ControllerFXML controllerFXML){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileFXML));
            loader.setController(controllerFXML);
            stage.setScene(new Scene(loader.load(), 800, 400));
            stage.show();
        }catch (IOException e) {

        }
    }

}
