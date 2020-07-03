package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementManager;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe che ha il compito di fare da controller alla  MovementGUI.
 */
public class ControllerMovementGUI implements ControllerFXML {

    /**
     * Controller
     */
    private Controller controller;
    /**
     * ID della transazione in cui e' contenuto il movimento
     */
    private int IDtransaction;
    /**
     * Text Field per inserire l'ID del movimento
     */
    @FXML private TextField idMovement;
    /**
     * Text Field per inserire l'amount del movimento
     */
    @FXML private TextField amount;
    /**
     * ChoiceBox per selezionare l'account
     */
    @FXML private ChoiceBox<Account> accountName;
    /**
     * ChoiceBox per selezionare il tipo di movimento
     */
    @FXML private ChoiceBox<MovementType> movType;
    /**
     * ChoiceBox per selezionare il tag
     */
    @FXML private ChoiceBox<Tag> tagName;
    /**
     * Text Field per inserire la descrizione
     */
    @FXML private TextField description;
    /**
     * Label per le notifiche del movimento
     */
    @FXML private Label notificationMovement;
    /**
     * Tabella dei movimenti
     */
    @FXML private TableView<Movement> movementsTable;
    /**
     * Colonna ID movimento
     */
    @FXML private TableColumn<Movement,Integer> idColumn;
    /**
     * Colonna amount movimento
     */
    @FXML private TableColumn<Movement,Double> amountColumn;
    /**
     * Colonna tag movimento
     */
    @FXML private TableColumn<Movement,String> tagColumn;
    /**
     * Colonna account del movimento
     */
    @FXML private TableColumn<Movement,String> accColumn;
    /**
     * Colonna tipo di movimento
     */
    @FXML private TableColumn<Movement,MovementType> movTypeColumn;
    /**
     * Colonna per la descrizione del movimento
     */
    @FXML private TableColumn<Movement,String> descriptionColumn;
    /**
     * Observable list di movimenti
     */
    private ObservableList<Movement> lMovement;
    /**
     * Observable list di MovementType
     */
    private ObservableList<MovementType> lMovementType;
    /**
     * Observable list di tag
     */
    private ObservableList<Tag> lTag;
    /**
     * Observable list di account
     */
    private ObservableList<Account> lAccount;

    /**
     * Costruttore di ControllerMovementGUI
     * @param controller
     * @param IDt
     */
    public ControllerMovementGUI(Controller controller,int IDt) {
        this.controller = controller;
        this.IDtransaction = IDt;
    }

    /**
     * Metodo per inizializzare le variabili
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lMovement = FXCollections.observableArrayList();
        lMovementType = FXCollections.observableArrayList();
        lTag = FXCollections.observableArrayList();
        lAccount = FXCollections.observableArrayList();
        lTag.addAll(controller.getTags());
        lAccount.addAll(controller.getAccounts());
        lMovementType.add(MovementType.DEBIT);
        lMovementType.add(MovementType.CREDITS);
        movType.setItems(lMovementType);
        tagName.setItems(lTag);
        accountName.setItems(lAccount);
        updateMovements();
    }

    /**
     * Metodo per aggiungere un movimento
     */
    @FXML
    public void addMovement(){
        try {
            if(controller.getBudgetReport().getLedger().getSingleMovement(Integer.parseInt(idMovement.getText()),IDtransaction) == null
                && Integer.parseInt(idMovement.getText()) > 0) {
                if (amount.getText() != null && accountName.getValue() != null &&
                        movType.getValue() != null && tagName.getValue() != null) {
                    Movement m = MovementManager.createMovement(Integer.parseInt(idMovement.getText()),
                            Double.parseDouble(amount.getText()), description.getText(), accountName.getValue(), tagName.getValue(),
                            movType.getValue(), controller.getBudgetReport().getLedger().getSingleTransaction(IDtransaction));
                    controller.addMovement(m);
                    updateMovements();
                }
            }
            else{
                notificationMovement.setText("Error ID!");
            }
        }catch (Exception e){
            notificationMovement.setText("Operation Failed!");
        }finally {
            idMovement.clear();
            amount.clear();
            description.clear();
        }
    }

    /**
     * Metodo per resettare i Text Field
     */
    @FXML
    public void clearMovement(){
        idMovement.clear();
        amount.clear();
        description.clear();
    }

    /**
     * Metodo per eliminare un movimento
     */
    @FXML
    public void deleteMovement(){
        Movement m = movementsTable.getSelectionModel().getSelectedItem();
        if(!movementsTable.getItems().isEmpty() && m != null) {
            controller.removeMovement(m);
            updateMovements();
        }
    }

    /**
     * Metodo per aggiornare la tabella dei movimenti
     */
    private void updateMovements(){
        notificationMovement.setText(" ");
        lMovement.removeAll(lMovement);
        lMovement.addAll(controller.getBudgetReport().getLedger().getSingleTransaction(IDtransaction).getMovements());
        movementsTable.setItems(lMovement);
        idColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        amountColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));
        tagColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTag().getName()));
        accColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAccount().getName()));
        movTypeColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getMovementType()));
        descriptionColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        movementsTable.refresh();
    }
}
