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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe che ha il compito di fare da controller alla AccountGUI.
 */
public class ControllerAccountGUI implements ControllerFXML {

    /**
     * Controller
     */
    private Controller controller;
    /**
     * Text Field per nome dell'account
     */
    @FXML private TextField nameAccount;
    /**
     * Text Field per ID dell'account
     */
    @FXML private TextField idAccount;
    /**
     * Text Field per opening balance dell'account
     */
    @FXML private TextField obAccount;
    /**
     * Text Field per descrizione dell'account
     */
    @FXML private TextField description;
    /**
     * ChoiceBox per il tipo di account
     */
    @FXML private ChoiceBox<AccountType> accountType;
    /**
     * Label per le notifiche dell'Account
     */
    @FXML private Label notificationAccount;
    /**
     * Tabella degli accounts
     */
    @FXML private TableView<Account> accountTable;
    /**
     * Colonna ID dell'account
     */
    @FXML private TableColumn<Account,Integer> idColumn;
    /**
     * Colonna nome dell'account
     */
    @FXML private TableColumn<Account,String> nameColumn;
    /**
     * Colonna bilancio dell'account
     */
    @FXML private TableColumn<Account,Double> balanceColumn;
    /**
     * Colonna per il tipo di account
     */
    @FXML private TableColumn<Account,AccountType> accTypeColumn;
    /**
     * Colonna per la descrizione dell'account
     */
    @FXML private TableColumn<Account,String> descriptionColumn;
    /**
     * Observable list AccountType
     */
    private ObservableList<AccountType> lAccountType;
    /**
     * Observable list di account
     */
    private ObservableList<Account> lAccount;

    /**
     * Costruttore di ControllerAccountGUI
     * @param controller
     */
    public ControllerAccountGUI(Controller controller) {
        this.controller = controller;
    }

    /**
     * Metodo che ha il compito di inizializzare le variabili
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lAccount = FXCollections.observableArrayList();
        lAccountType = FXCollections.observableArrayList();
        lAccountType.add(AccountType.ASSETS);
        lAccountType.add(AccountType.LIABILITIES);
        accountType.setItems(lAccountType);
        updateAccounts();
    }

    /**
     * Metodo per aggiungere un account
     */
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
        }
    }

    /**
     * Metodo per resettare i Text Field
     */
    @FXML
    public void clearAccount(){
        idAccount.clear();
        nameAccount.clear();
        description.clear();
        obAccount.clear();
    }

    /**
     * Metodo per eliminare un account
     */
    @FXML
    public void deleteAccount(){
        Account account = accountTable.getSelectionModel().getSelectedItem();
        if(!accountTable.getItems().isEmpty()&&account!=null) {
            controller.removeAccount(account);
            updateAccounts();
        }
    }

    /**
     * Metodo per aggiornare la tabella degli accounts
     */
    private void updateAccounts(){
        notificationAccount.setText(" ");
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
