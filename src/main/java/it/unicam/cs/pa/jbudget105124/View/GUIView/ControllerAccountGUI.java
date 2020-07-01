package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountManager;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAccountGUI implements ControllerFXML {

    private Controller controller;
    @FXML private TextField nameAccount;
    @FXML private TextField idAccount;
    @FXML private TextField obAccount;
    @FXML private TextField description;
    @FXML private ChoiceBox<AccountType> accountType;
    @FXML private Label notificationAccount;
    @FXML private TableView<Account> accountTable;
    @FXML private TableColumn<Account,Integer> idColumn;
    @FXML private TableColumn<Account,String> nameColumn;
    @FXML private TableColumn<Account,Double> balanceColumn;
    @FXML private TableColumn<Account,AccountType> accTypeColumn;
    @FXML private TableColumn<Account,String> descriptionColumn;
    private ObservableList<AccountType> lAccountType;
    private ObservableList<Account> lAccount;

    public ControllerAccountGUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lAccount = FXCollections.observableArrayList();
        lAccountType = FXCollections.observableArrayList();
        lAccountType.add(AccountType.ASSETS);
        lAccountType.add(AccountType.LIABILITIES);
        accountType.setItems(lAccountType);
        updateAccounts();
    }

    @FXML
    public void addAccount(){
        try {
            if(controller.getBudgetReport().getLedger().getSingleAccount(Integer.parseInt(idAccount.getText())) == null
                && Integer.parseInt(idAccount.getText()) > 0 ) {
                if (nameAccount.getText() != null && obAccount.getText() != null && accountType.getValue() != null) {
                    Account account = AccountManager.createAccount(Integer.parseInt(idAccount.getText()),
                            nameAccount.getText(), description.getText(), Double.parseDouble(obAccount.getText()),
                            accountType.getValue());
                    controller.addAccount(account);
                    updateAccounts();
                }
            }
            else{
                notificationAccount.setText("Error ID!");
            }

        }catch (Exception e){
            notificationAccount.setText("Operation Failed!");
        }finally {
            idAccount.clear();
            nameAccount.clear();
            description.clear();
            obAccount.clear();
            //accountType.clear();
        }
    }

    @FXML
    public void clearAccount(){
        idAccount.clear();
        nameAccount.clear();
        description.clear();
        obAccount.clear();
        //accountType.clear();
    }


    @FXML
    public void deleteAccount(){
        Account account = accountTable.getSelectionModel().getSelectedItem();
        if(!accountTable.getItems().isEmpty()&&account!=null) {
            controller.removeAccount(account);
            updateAccounts();
        }
    }

    private void updateAccounts(){
        lAccount.removeAll(lAccount);
        lAccount.addAll(controller.getAccounts());
        accountTable.setItems(lAccount);
        idColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        nameColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        balanceColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getBalance()));
        accTypeColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAccountType()));
        descriptionColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        accountTable.refresh();
    }
}
