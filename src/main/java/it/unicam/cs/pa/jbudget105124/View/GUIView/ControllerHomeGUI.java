package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Controller.ControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHomeGUI implements ControllerFXML {

    Controller controller = ControllerManager.createController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void accountInterfaces() {
        openWindow("Account","/AccountInterface.fxml",new ControllerAccountGUI(controller));
    }

    @FXML
    public void tagInterface() {
        openWindow("Tag","/TagInterface.fxml",new ControllerTagGUI(controller));
    }

    @FXML
    public void transactionInterface() {
        openWindow("Transaction","/TransactionInterface.fxml",new ControllerTransactionGUI(controller));
    }

    @FXML
    public void budgetInterface() {
        openWindow("Budget","/BudgetInterface.fxml",new ControllerBudgetGUI(controller));
    }

    @FXML
    public void schedule(){

    }
}
