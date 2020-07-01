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

public class ControllerMovementGUI implements ControllerFXML {

    private Controller controller;
    private int IDtransaction;
    @FXML private TextField idMovement;
    @FXML private TextField amount;
    @FXML private ChoiceBox<Account> accountName;
    @FXML private ChoiceBox<MovementType> movType;
    @FXML private ChoiceBox<Tag> tagName;
    @FXML private TextField description;
    @FXML private Label notificationMovement;
    @FXML private TableView<Movement> movementsTable;
    @FXML private TableColumn<Movement,Integer> idColumn;
    @FXML private TableColumn<Movement,Double> amountColumn;
    @FXML private TableColumn<Movement,String> tagColumn;
    @FXML private TableColumn<Movement,String> accColumn;
    @FXML private TableColumn<Movement,MovementType> movTypeColumn;
    @FXML private TableColumn<Movement,String> descriptionColumn;
    private ObservableList<Movement> lMovement;
    private ObservableList<MovementType> lMovementType;
    private ObservableList<Tag> lTag;
    private ObservableList<Account> lAccount;

    public ControllerMovementGUI(Controller controller,int IDt) {
        this.controller = controller;
        this.IDtransaction = IDt;
    }

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

    @FXML
    public void addMovement(){
        try {
            if(controller.getBudgetReport().getLedger().getSingleMovement(Integer.parseInt(idMovement.getText()),IDtransaction) == null
                && Integer.parseInt(idMovement.getText()) > 0) {
                if (amount.getText() != null && accountName.getValue() != null &&
                        movType.getValue() != null && tagName.getValue() != null) {
                    Movement m = MovementManager.createMovement(Integer.parseInt(idMovement.getText()),
                            Double.parseDouble(amount.getText()), description.getText(), accountName.getValue(), tagName.getValue(),
                            movType.getValue(), controller.getTransaction(IDtransaction));
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
            //date.clear();
        }
    }

    @FXML
    public void clearTransaction(){
        idMovement.clear();
        amount.clear();
        description.clear();
        //date.clear();
    }

    @FXML
    public void deleteTransaction(){
        Movement m = movementsTable.getSelectionModel().getSelectedItem();
        if(!movementsTable.getItems().isEmpty() && m != null) {
            controller.removeMovement(m);
            updateMovements();
        }
    }

    private void updateMovements(){
        lMovement.removeAll(lMovement);
        lMovement.addAll(controller.getTransaction(IDtransaction).getMovements());
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
