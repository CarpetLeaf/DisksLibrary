package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.Audiobook;
import sample.DatabaseHandler;
import sample.Document;

public class AddDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void initialize() {
        type_id.getItems().addAll("CD-ROM", "CD-R", "DVD", "BD");
        place_type_id.getItems().addAll("стойка", "полка", "ящик");
        place_type_id.setOnAction(actionEvent -> {
            choicePlaceType();
        });
        ok_button_id.setOnAction(actionEvent -> {
            addDocument();
        });
    }

    private void addDocument() {
        try {
            DatabaseHandler handler = new DatabaseHandler();
            int place_id = handler.getPlace(place_type_id.getValue(), place_num_id.getValue());
            String name = name_id.getText();
            int volume = Integer.parseInt(volume_id.getText());
            String date = date_id.getValue().toString();
            String media_type = type_id.getValue();
            String publisher = publisher_id.getText();
            double price = Double.parseDouble(price_id.getText());
            Document document = new Document(place_id, name, volume, date, media_type, publisher, price);
            handler.addDocument(document);
            status_id.setText("ОК");
            status_id.setTextFill(Color.GREEN);
            status_id.setVisible(true);
            if(type_id.getValue() == null){
                status_id.setText("Ошибка ввода");
                status_id.setTextFill(Color.RED);
                status_id.setVisible(true);
            }
        }catch (NumberFormatException | NullPointerException ex){
            status_id.setText("Ошибка ввода");
            status_id.setTextFill(Color.RED);
            status_id.setVisible(true);
        }
    }

    private void choicePlaceType(){
        place_num_id.getItems().clear();
        DatabaseHandler handler = new DatabaseHandler();
        place_num_id.getItems().addAll(handler.getAllNumbers(place_type_id.getValue()));
        place_num_id.setDisable(false);
    }

}
