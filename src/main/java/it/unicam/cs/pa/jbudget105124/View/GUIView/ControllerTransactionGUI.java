package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.TransactionManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Classe che ha il compito di fare da controller alla TransactionGUI.
 */
public class ControllerTransactionGUI implements ControllerFXML {

    /**
     * Controller
     */
    private Controller controller;
    /**
     * Text Field per inserire l'ID della transazione
     */
    @FXML private TextField idTransaction;
    /**
     * Text Field per inserire la descrizione della transazione
     */
    @FXML private TextField description;
    /**
     * Date Picker per inserire la data
     */
    @FXML private DatePicker date;
    /**
     * Label per le notifiche della transazione
     */
    @FXML private Label notificationTransaction;
    /**
     * Tabella delle transazioni
     */
    @FXML private TableView<Transaction> transactionsTable;
    /**
     * Colonna ID della transazione
     */
    @FXML private TableColumn<Transaction,Integer> idColumn;
    /**
     * Colonna data della transazione
     */
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    /**
     * Colonna amount della transazione
     */
    @FXML private TableColumn<Transaction,Double> amountColumn;
    /**
     * Colonna descrizione della transazione
     */
    @FXML private TableColumn<Transaction,String> descriptionColumn;
    private ObservableList<Transaction> lTransaction;

    /**
     * Controllore di ControllerTransactionGUI
     * @param controller
     */
    public ControllerTransactionGUI(Controller controller) {
        this.controller = controller;
    }

    /**
     * Metodo per inizializzare tutte le variabili
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lTransaction = FXCollections.observableArrayList();
        updateTransactions();
    }

    /**
     * Metodo per aggiungere una transazione
     */
    @FXML
    public void addTransaction(){
        try {
            if(controller.getBudgetReport().getLedger().getSingleTransaction(Integer.parseInt(idTransaction.getText())) == null
                && Integer.parseInt(idTransaction.getText()) > 0) {
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
        }
    }

    /**
     * Metodo per resettare i Text Field
     */
    @FXML
    public void clearTransaction(){
        idTransaction.clear();
        description.clear();
    }

    /**
     * Metodo per eliminare una transazione
     */
    @FXML
    public void deleteTransaction(){
        Transaction t = transactionsTable.getSelectionModel().getSelectedItem();
        if(!transactionsTable.getItems().isEmpty()&&t!=null) {
            controller.removeTransaction(t);
            updateTransactions();
        }
    }

    /**
     * Metodo per aprire la finestra movimenti
     */
    @FXML
    public void movementInterface() {
        Transaction t = transactionsTable.getSelectionModel().getSelectedItem();
        if(!transactionsTable.getItems().isEmpty()&&t!=null) {
            openWindow("Movement","/MovementInterface.fxml",new ControllerMovementGUI(controller,t.getID()));
        }
    }

    /**
     * Metodo per aggiornare la tabella delle transazioni
     */
    private void updateTransactions(){
        notificationTransaction.setText(" ");
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
