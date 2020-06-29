package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerBudgetGUI implements ControllerFXML {

    private Controller controller;
    @FXML private TextField idTag;
    @FXML private Label notification;

    public ControllerBudgetGUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addTag() {
        notification.setText(idTag.getText());
        idTag.clear();
    }
}
