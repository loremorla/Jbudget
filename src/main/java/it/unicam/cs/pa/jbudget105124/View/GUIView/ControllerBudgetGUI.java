package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Classe che ha il compito di fare da controller alla  BudgetGUI.
 */
public class ControllerBudgetGUI implements ControllerFXML {

    /**
     * Controller
     */
    private Controller controller;
    /**
     * ChoiceBox per selezionare il tag
     */
    @FXML private ChoiceBox<Tag> Tag;
    /**
     * Text Field per impostare il budget ad tag
     */
    @FXML private TextField estimatedAmount;
    /**
     * Label per le notifiche del budget
     */
    @FXML private Label notificationBudget;
    /**
     * Tabella dei budget con real amount del tag ed il report
     */
    @FXML private TableView<Map.Entry<Tag,Double>> budgetTable;
    /**
     * Colonna del tag
     */
    @FXML private TableColumn<Map.Entry<Tag,Double>,Tag> tagColumn;
    /**
     * Colonna del report
     */
    @FXML private TableColumn<Map.Entry<Tag,Double>,Double> totalColumn;
    /**
     * Colonna del budget per il tag inserito dall'utente
     */
    @FXML private TableColumn<Map.Entry<Tag,Double>,Double> estAmountColumn;
    /**
     * Colonna per il real amount del tag
     */
    @FXML private TableColumn<Map.Entry<Tag,Double>,Double> realAmountColumn;
    /**
     * Observable list di budget
     */
    private ObservableList<Map.Entry<Tag,Double>> lBudget;
    /**
     * Observable list di tag
     */
    private ObservableList<Tag> lTag;

    /**
     * Costruttore di ControllerBudgetGUI
     * @param controller
     */
    public ControllerBudgetGUI(Controller controller) {
        this.controller = controller;
    }

    /**
     * Metodo che il compito di inizializzare le variabili
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lBudget = FXCollections.observableArrayList();
        lTag = FXCollections.observableArrayList();
        lTag.addAll(controller.getTags());
        Tag.setItems(lTag);
        updateBudget();
    }

    /**
     * Metodo per aggiungere un budget
     */
    public void addBudget() {
        try {
            if (estimatedAmount.getText() != null) {
                Tag t = Tag.getValue();
                double value = Double.parseDouble(estimatedAmount.getText());
                controller.addBudgetTag(t,value);
                updateBudget();
            }
        }catch (Exception e){
            notificationBudget.setText("Operation Failed!");
        }finally {
            estimatedAmount.clear();
        }
    }

    /**
     * Metodo per resettare i text Field
     */
    @FXML
    public void clearBudget(){
        estimatedAmount.clear();
    }

    /**
     * Metodo per eliminare un tag
     */
    @FXML
    public void deleteBudget(){
        Tag tag = budgetTable.getSelectionModel().getSelectedItem().getKey();
        if(!budgetTable.getItems().isEmpty() & tag != null) {
            controller.removeBudgetTag(tag);
            refreshBudget();
        }
    }

    /**
     * Metodo per far refresh della finestra
     */
    @FXML
    public void refreshBudget(){
        updateBudget();
        lTag.removeAll(lTag);
        lTag.addAll(controller.getTags());
        Tag.setItems(lTag);
    }

    /**
     * Metodo per aggiornare la tabella dei budget
     */
    private void updateBudget(){
        notificationBudget.setText(" ");
        lBudget.removeAll(lBudget);
        lBudget.addAll(controller.getBudgetTags().entrySet());
        budgetTable.setItems(lBudget);
        tagColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey()));
        estAmountColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue()));
        realAmountColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey().getTotalAmount()));
        totalColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(controller.getBudgetReport()
                        .report().get(cellData.getValue().getKey())));
        budgetTable.refresh();
    }
}
