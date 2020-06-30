package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Controller.ControllerManager;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Store.TxtReader;
import it.unicam.cs.pa.jbudget105124.Model.Store.TxtWriter;
import it.unicam.cs.pa.jbudget105124.Model.Store.Writer;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ControllerHomeGUI implements ControllerFXML {

    private Controller controller = ControllerManager.createController();
    @FXML private RadioButton tagButton;
    @FXML private RadioButton dateButton;
    @FXML private DatePicker dateFrom;
    @FXML private DatePicker dateTo;
    @FXML private ChoiceBox<Tag> tagChoice;
    @FXML private Label notificationHome;
    @FXML private TableView<Transaction> transactionsTable;
    @FXML private TableColumn<Transaction,Integer> idColumn;
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML private TableColumn<Transaction,Boolean> stateColumn;
    @FXML private TableColumn<Transaction,Double> amountColumn;
    @FXML private TableColumn<Transaction,String> descriptionColumn;
    private ObservableList<Transaction> lScheduledTr;
    private ObservableList<Tag> lTag;
    private Writer writer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lScheduledTr = FXCollections.observableArrayList();
        lTag = FXCollections.observableArrayList();
        lTag.addAll(controller.getTags());
        tagChoice.setItems(lTag);
        updateTransactions();
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
    public void schedule() {
        if(tagButton.isSelected() && tagChoice.getValue() != null) {
            lScheduledTr.setAll(this.controller.scheduledTransactionsTag(tagChoice.getValue()));
            //logger.info("Transactions scheduled by tag: ["+tagSchedChoice.getValue().toString()+"]");
        }
        if(dateButton.isSelected() && dateFrom.getValue() != null && dateTo.getValue() != null) {
            lScheduledTr.setAll(this.controller.scheduledTransactionsDate(dateFrom.getValue(),dateTo.getValue()));
            //logger.info("Transactions scheduled by date: ["+dateStart.getValue()+","+dateStop.getValue()+"]");
        }
        if(!tagButton.isSelected() && !dateButton.isSelected()) refreshTransaction();
        transactionsTable.refresh();
    }

    public void checkTransaction(){
        for(Transaction t : controller.getBudgetReport().getLedger().getTransactions()){
            if(t.getState() == false){
                if(!t.getDate().isAfter(LocalDate.now())) {
                    t.completed();
                    for (Movement m : t.getMovements()) {
                        controller.addMovement(m);
                    }
                }
            }
        }
    }

    private void updateTransactions(){
        lScheduledTr.removeAll(lScheduledTr);
        lScheduledTr.addAll(controller.getTransactions());
        transactionsTable.setItems(lScheduledTr);
        idColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        dateColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        amountColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalAmount()));
        descriptionColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        transactionsTable.refresh();
    }

    @FXML
    public void refreshTransaction(){
        updateTransactions();
        lTag.removeAll(lTag);
        lTag.addAll(controller.getTags());
        tagChoice.setItems(lTag);
    }

    @FXML
    public void newReport() {
        try {
            clear();
            String path = createFileChooser().showSaveDialog(new Stage()).getAbsolutePath();
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
            refreshTransaction();
            checkTransaction();
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

    public void save(){
        try {
            this.controller.write(writer);
        } catch (IOException e) {

        }
    }
}
