package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Document;
import sample.Music;

public class FormDocument {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel_id;

    @FXML
    private DatePicker date_id;

    @FXML
    private TextField name_id;

    @FXML
    private Button ok_button_id;

    @FXML
    private ComboBox<Integer> place_num_id;

    @FXML
    private ComboBox<String> place_type_id;

    @FXML
    private TextField price_id;

    @FXML
    private TextField publisher_id;

    @FXML
    private Label status_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private TextField volume_id;

    @FXML
    private Button clear_id;

    private Document doc;

    @FXML
    void initialize() {
        type_id.getItems().addAll("CD-ROM", "CD-R", "DVD", "BD");
        place_type_id.getItems().addAll("стойка", "полка", "ящик");
        place_type_id.setOnAction(actionEvent -> {
            choicePlaceType();
        });
        cancel_id.setOnAction(actionEvent -> {
            Stage stage = (Stage) cancel_id.getScene().getWindow();
            stage.close();
        });
        clear_id.setOnAction(actionEvent -> {
            date_id.setValue(null);
        });
        ok_button_id.setOnAction(actionEvent -> {
            if(checkFields()){
                DatabaseHandler handler = new DatabaseHandler();
                Document doc = new Document();
                doc.setMyId(this.doc.getMyId());
                doc.setPlace_id(handler.getPlace(place_type_id.getValue(), place_num_id.getValue()));
                doc.setName(name_id.getText().trim());
                doc.setVolume(Integer.parseInt(volume_id.getText().trim()));
                doc.setDate(date_id.getValue().toString().trim());
                doc.setMedia_type(type_id.getValue());
                doc.setPublisher(publisher_id.getText().trim());
                doc.setPrice(Double.parseDouble(price_id.getText().trim()));
                handler.updateDocument(doc);
            }
            else System.out.println("Error");
        });
    }

    public void setFormDocument(Document doc){
        this.doc = doc;
        name_id.setText(doc.getName());
        volume_id.setText(String.valueOf(doc.getVolume()));
        date_id.setValue(LocalDate.parse(doc.getDate()));
        type_id.setValue(doc.getMedia_type());
        publisher_id.setText(doc.getPublisher());
        price_id.setText(Double.toString(doc.getPrice()));
        DatabaseHandler handler = new DatabaseHandler();
        place_type_id.setValue(handler.getTypePlace(doc.getPlace_id()));
        place_num_id.setDisable(false);
        choicePlaceType();
        place_num_id.setValue(handler.getNumPlace(doc.getPlace_id()));
    }

    private void choicePlaceType(){
        place_num_id.getItems().clear();
        DatabaseHandler handler = new DatabaseHandler();
        place_num_id.getItems().addAll(handler.getAllNumbers(place_type_id.getValue()));
        place_num_id.setDisable(false);
    }

    private boolean checkFields(){
        boolean res = true;
        try{
            if(!(volume_id.getText() == null || volume_id.getText().isEmpty())){
                int x = Integer.parseInt(volume_id.getText());
                if(x < 0 || x >= Math.pow(10, 9) - 1)
                    return false;
            }
            if(!(date_id.getValue() == null || date_id.getValue().toString().trim().isEmpty()))
                Date.valueOf(date_id.getValue().toString());
            if (!(price_id.getText() == null || price_id.getText().trim().isEmpty())){
                Double.parseDouble(price_id.getText());
                double x = Double.parseDouble(price_id.getText());
                if(x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
                String[] k = String.valueOf(x).split("\\.");
                if (k[1].length()>2)
                    return false;
            }
        }catch (IllegalArgumentException | NullPointerException ex){
            res = false;
        }
        return res;
    }

}
