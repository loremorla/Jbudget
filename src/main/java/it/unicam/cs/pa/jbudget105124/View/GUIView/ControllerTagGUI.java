package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.LedgerBasic;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Tag.TagManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTagGUI implements ControllerFXML {

    private Controller controller;
    @FXML private TextField nameTag;
    @FXML private TextField idTag;
    @FXML private Label notificationTag;
    @FXML private TableView<Tag> tagTable;
    @FXML private TableColumn<Tag,Integer> idColumn;
    @FXML private TableColumn<Tag,String> nameColumn;
    @FXML private TableColumn<Tag,Double> amountColumn;
    private ObservableList<Tag> lTag;

    public ControllerTagGUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lTag = FXCollections.observableArrayList();
        updateTags();
    }

    @FXML
    public void addTag(){
        try {
            if (nameTag.getText() != null) {
                Tag tag = TagManager.createTag(nameTag.getText(), Integer.parseInt(idTag.getText()));
                controller.addTag(tag);
                updateTags();
            }
        }catch (Exception e){
            notificationTag.setText("Operation Failed!");
        }finally {
            nameTag.clear();
            idTag.clear();
        }
    }

    @FXML
    public void clearTag(){
        nameTag.clear();
        idTag.clear();
    }

    @FXML
    public void refreshTag(){
        updateTags();
    }

    @FXML
    public void deleteTag(){
        Tag tag = tagTable.getSelectionModel().getSelectedItem();
        if(!tagTable.getItems().isEmpty()&&tag!=null) {
            controller.removeTag(tag);
            updateTags();
        }
    }

    private void updateTags(){
        lTag.removeAll(lTag);
        lTag.addAll(controller.getTags());
        tagTable.setItems(lTag);
        idColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        nameColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        amountColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalAmount()));
        tagTable.refresh();
    }

}
