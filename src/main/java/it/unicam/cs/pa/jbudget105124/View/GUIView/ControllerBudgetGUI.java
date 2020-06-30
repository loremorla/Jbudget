package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Tag.TagManager;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerBudgetGUI implements ControllerFXML {

    private Controller controller;
    @FXML private ChoiceBox<Tag> Tag;
    @FXML private TextField estimatedAmount;
    @FXML private Label notificationBudget;
    @FXML private TableView<Map.Entry<Tag,Double>> budgetTable;
    @FXML private TableColumn<Map.Entry<Tag,Double>,Tag> tagColumn;
    @FXML private TableColumn<Map.Entry<Tag,Double>,Double> totalColumn;
    @FXML private TableColumn<Map.Entry<Tag,Double>,Double> estAmountColumn;
    @FXML private TableColumn<Map.Entry<Tag,Double>,Double> realAmountColumn;
    private ObservableList<Map.Entry<Tag,Double>> lBudget;
    private ObservableList<Tag> lTag;

    public ControllerBudgetGUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lBudget = FXCollections.observableArrayList();
        lTag = FXCollections.observableArrayList();
        lTag.addAll(controller.getTags());
        Tag.setItems(lTag);
        updateBudget();
    }

    public void addTag() {
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
            //idTag.clear();
        }
    }

    @FXML
    public void clearBudget(){
        estimatedAmount.clear();
        //accountType.clear();
    }

    @FXML
    public void deleteBudget(){
        Tag tag = budgetTable.getSelectionModel().getSelectedItem().getKey();
        if(!budgetTable.getItems().isEmpty() & tag != null) {
            controller.removeBudgetTag(tag);
            updateBudget();
        }
    }

    @FXML
    public void refreshBudget(){
        updateBudget();
        lTag.removeAll(lTag);
        lTag.addAll(controller.getTags());
        Tag.setItems(lTag);
    }

    private void updateBudget(){
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
