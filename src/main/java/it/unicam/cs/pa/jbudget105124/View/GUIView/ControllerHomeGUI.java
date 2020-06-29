package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Controller.ControllerManager;
import it.unicam.cs.pa.jbudget105124.Model.Store.TxtReader;
import it.unicam.cs.pa.jbudget105124.Model.Store.TxtWriter;
import it.unicam.cs.pa.jbudget105124.Model.Store.Writer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHomeGUI implements ControllerFXML {

    private Controller controller = ControllerManager.createController();

    private Writer writer;

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

    @FXML
    public void newReport() {
        try {
            String path = createFileChooser().showSaveDialog(new Stage()).getAbsolutePath();
            clear();
            this.writer = new TxtWriter(path);
            this.controller.write(this.writer);
        } catch (Exception e) {

        }
    }

    @FXML
    public void clear() {
        try {
            this.controller.resetReport();
        } catch (Exception e) {

        }
    }

    @FXML
    public void load() {
        try {
            String path = createFileChooser().showOpenDialog(new Stage()).getAbsolutePath();
            this.controller.read(new TxtReader(path));
        } catch (Exception e) {

        }
    }

    @FXML
    public void store() {
        try {
            String path = createFileChooser().showSaveDialog(new Stage()).getAbsolutePath();
            this.writer = new TxtWriter(path);
            save();
        } catch (Exception e) {

        }
    }

    private FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        fileChooser.setInitialFileName("JBudget");
        return fileChooser;
    }

    private void save(){
        try {
            this.controller.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
