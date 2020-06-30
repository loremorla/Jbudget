package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.TransactionBasic;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.TransactionManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerTransactionGUI implements ControllerFXML {

    private Controller controller;
    @FXML private TextField idTransaction;
    @FXML private TextField description;
    @FXML private DatePicker date;
    @FXML private Label notificationTransaction;
    @FXML private TableView<Transaction> transactionsTable;
    @FXML private TableColumn<Transaction,Integer> idColumn;
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML private TableColumn<Transaction,Double> amountColumn;
    @FXML private TableColumn<Transaction,String> descriptionColumn;
    private ObservableList<Transaction> lTransaction;

    public ControllerTransactionGUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lTransaction = FXCollections.observableArrayList();
        updateTransactions();
    }

    @FXML
    public void addTransaction(){
        try {
            if(controller.getBudgetReport().getLedger().getSingleTransaction(Integer.parseInt(idTransaction.getText())) == null) {
                if (date.getValue() != null) {
                    Transaction t = TransactionManager.createTransaction(Integer.parseInt(idTransaction.getText()),
                            description.getText(), date.getValue());
                    controller.addTransaction(t);
                    updateTransactions();
                }
            }
            else{
                notificationTransaction.setText("Duplicate ID!");
            }
        }catch (Exception e){
            notificationTransaction.setText("Operation Failed!");
        }finally {
            idTransaction.clear();
            description.clear();
            //date.clear();
        }
    }

    @FXML
    public void clearTransaction(){
        idTransaction.clear();
        description.clear();
        //date.clear();
    }

    @FXML
    public void refreshTransaction(){
        updateTransactions();
    }

    @FXML
    public void deleteTransaction(){
        Transaction t = transactionsTable.getSelectionModel().getSelectedItem();
        if(!transactionsTable.getItems().isEmpty()&&t!=null) {
            controller.removeTransaction(t);
            updateTransactions();
        }
    }

    @FXML
    public void movementInterface() {
        Transaction t = transactionsTable.getSelectionModel().getSelectedItem();
        if(!transactionsTable.getItems().isEmpty()&&t!=null) {
            openWindow("Movement","/MovementInterface.fxml",new ControllerMovementGUI(controller,t.getID()));
        }
    }

    private void updateTransactions(){
        lTransaction.removeAll(lTransaction);
        lTransaction.addAll(controller.getTransactions());
        transactionsTable.setItems(lTransaction);
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
}
