package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Controller.ControllerManager;
import it.unicam.cs.pa.jbudget105124.Model.Store.TxtReader;
import it.unicam.cs.pa.jbudget105124.Model.Store.TxtWriter;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Classe che ha il compito di fare da controller alla  HomeGUI; da qui si potranno raggiungere
 * le altre interfaccie dell'applicazione.
 */
public class ControllerHomeGUI implements ControllerFXML {

    /**
     * Variabile per i logger
     */
    private final static Logger logger = Logger.getGlobal();

    /**
     * Controller
     */
    private Controller controller = ControllerManager.createController();
    /**
     * bottone selezione per filtro tag
     */
    @FXML private RadioButton tagButton;
    /**
     * bottone selezione per filtro data
     */
    @FXML private RadioButton dateButton;
    /**
     * picker per selezionare data iniziale per filtrare le transazioni
     */
    @FXML private DatePicker dateFrom;
    /**
     * picker per selezionare data finale per filtrare le transazioni
     */
    @FXML private DatePicker dateTo;
    /**
     * choicebox del tag per filtrare le transazioni
     */
    @FXML private ChoiceBox<Tag> tagChoice;
    /**
     * label per notifiche
     */
    @FXML private Label notificationHome;
    /**
     * tabella transazioni
     */
    @FXML private TableView<Transaction> transactionsTable;
    /**
     * colonna id transazioni
     */
    @FXML private TableColumn<Transaction,Integer> idColumn;
    /**
     * colonna data transazioni
     */
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    /**
     * colonna amount transazioni
     */
    @FXML private TableColumn<Transaction,Double> amountColumn;
    /**
     * colonna descrizione transazioni
     */
    @FXML private TableColumn<Transaction,String> descriptionColumn;
    /**
     * observable list transazioni
     */
    private ObservableList<Transaction> lScheduledTr;
    /**
     * observable list tag
     */
    private ObservableList<Tag> lTag;

    /**
     * Metodo che ha il compito di inizializzare le variabili
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lScheduledTr = FXCollections.observableArrayList();
        lTag = FXCollections.observableArrayList();
        lTag.addAll(controller.getTags());
        tagChoice.setItems(lTag);
        updateTransactions();
    }

    /**
     * Metodo per aprire la finestra account
     */
    @FXML
    public void accountInterfaces() {
        openWindow("Account","/AccountInterface.fxml",new ControllerAccountGUI(controller));
    }

    /**
     * Metodo per aprire la finestra tag
     */
    @FXML
    public void tagInterface() {
        openWindow("Tag","/TagInterface.fxml",new ControllerTagGUI(controller));
    }

    /**
     * Metodo per aprire la finestra transaction
     */
    @FXML
    public void transactionInterface() {
        openWindow("Transaction","/TransactionInterface.fxml",new ControllerTransactionGUI(controller));
    }

    /**
     * Metodo per aprire la finestra budget
     */
    @FXML
    public void budgetInterface() {
        openWindow("Budget","/BudgetInterface.fxml",new ControllerBudgetGUI(controller));
    }

    /**
     * Metodo per filtrate le transazioni in base a tag e data
     */
    @FXML
    public void schedule() {
        try {
            if (tagButton.isSelected() && tagChoice.getValue() != null) {
                lScheduledTr.setAll(this.controller.scheduledTransactionsTag(tagChoice.getValue()));
            }
            if (dateButton.isSelected() && dateFrom.getValue() != null && dateTo.getValue() != null) {
                lScheduledTr.setAll(this.controller.scheduledTransactionsDate(dateFrom.getValue(), dateTo.getValue()));
            }
            if (!tagButton.isSelected() && !dateButton.isSelected())
                refreshTransaction();
            transactionsTable.refresh();
        }catch (Exception e){
            logger.warning("Error in Schedule");
            notificationHome.setText("Error in Schedule");
        }
    }

    /**
     * Metodo per aggiornare la tabella delle transazioni
     */
    private void updateTransactions(){
        notificationHome.setText(" ");
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

    /**
     * Metodo per far refresh della finestra
     */
    @FXML
    public void refreshTransaction(){
        try {
            updateTransactions();
            lTag.removeAll(lTag);
            lTag.addAll(controller.getTags());
            tagChoice.setItems(lTag);
            notificationHome.setText(" ");
        }catch (Exception e){
            logger.warning("Error in Refresh");
            notificationHome.setText("Error in Refresh");
        }
    }

    /**
     * Metodo per creare un nuovo report
     */
    @FXML
    public void newReport() {
        try {
            this.controller.resetReport();
            refreshTransaction();
        } catch (Exception e) {
            logger.warning("Error in New");
            notificationHome.setText("Error in New");
        }
    }

    /**
     * Metodo per caricare un file
     */
    @FXML
    public void load() {
        try {
            String path = createFileChooser().showOpenDialog(new Stage()).getAbsolutePath();
            this.controller.read(new TxtReader(path));
            refreshTransaction();
        } catch (Exception e) {
            logger.warning("Error in Load");
            notificationHome.setText("Error in Load");
        }
    }

    /**
     * Metodo per salvare un file
     */
    @FXML
    public void store() {
        try {
            String path = createFileChooser().showSaveDialog(new Stage()).getAbsolutePath();
            this.controller.write(new TxtWriter(path));
        } catch (Exception e) {
            logger.warning("Error in Store");
            notificationHome.setText("Error in Store");
        }
    }

    /**
     * Metodo per creare un oggetto filechooser che permetterà di navigare
     * nel fyle system e così poter poi salavare e/o caricare file
     * @return
     */
    private FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        fileChooser.setInitialFileName("JBudget");
        return fileChooser;
    }
}
