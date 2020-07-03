package it.unicam.cs.pa.jbudget105124.View.GUIView;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
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

/**
 * Classe che ha il compito di fare da controller alla  TagGUI.
 */
public class ControllerTagGUI implements ControllerFXML {

    /**
     * Controller
     */
    private Controller controller;
    /**
     * Text Field per inserire il nome del tag
     */
    @FXML private TextField nameTag;
    /**
     * Text Field per inserire l'ID del tag
     */
    @FXML private TextField idTag;
    /**
     * Label per le notifiche del tag
     */
    @FXML private Label notificationTag;
    /**
     * Tabella dei tag
     */
    @FXML private TableView<Tag> tagTable;
    /**
     * Colonna ID del tag
     */
    @FXML private TableColumn<Tag,Integer> idColumn;
    /**
     * Colonna nome del tag
     */
    @FXML private TableColumn<Tag,String> nameColumn;
    /**
     * Observable list di tag
     */
    private ObservableList<Tag> lTag;

    /**
     * Costruttore di ControllerTagGUI
     * @param controller
     */
    public ControllerTagGUI(Controller controller) {
        this.controller = controller;
    }

    /**
     * Metodo per inizializzare le variabili
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lTag = FXCollections.observableArrayList();
        updateTags();
    }

    /**
     * Metodo per aggiungere un tag
     */
    @FXML
    public void addTag(){
        try {
            if(controller.getBudgetReport().getLedger().getSingleTag(Integer.parseInt(idTag.getText())) == null
                && Integer.parseInt(idTag.getText()) > 0) {
                if (nameTag.getText() != null) {
                    Tag tag = TagManager.createTag(nameTag.getText(), Integer.parseInt(idTag.getText()));
                    controller.addTag(tag);
                    updateTags();
                }
            }
            else{
                notificationTag.setText("Error ID!");
            }
        }catch (Exception e){
            notificationTag.setText("Operation Failed!");
        }finally {
            nameTag.clear();
            idTag.clear();
        }
    }

    /**
     * Metodo per resettare i Text Field
     */
    @FXML
    public void clearTag(){
        nameTag.clear();
        idTag.clear();
    }

    /**
     * Metodo per eliminare un tag
     */
    @FXML
    public void deleteTag(){
        Tag tag = tagTable.getSelectionModel().getSelectedItem();
        if(!tagTable.getItems().isEmpty()&&tag!=null) {
            controller.removeTag(tag);
            updateTags();
        }
    }

    /**
     * Metodo per aggiornare la tabella dei tag
     */
    private void updateTags(){
        notificationTag.setText(" ");
        lTag.removeAll(lTag);
        lTag.addAll(controller.getTags());
        tagTable.setItems(lTag);
        idColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        nameColumn.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        tagTable.refresh();
    }

}
