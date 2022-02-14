package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Audiobook;
import sample.DatabaseHandler;
import sample.Music;

public class AddAudiobookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> age_id;

    @FXML
    private TextField author_id;

    @FXML
    private TextArea description_id;

    @FXML
    private Spinner<Integer> hours_id;

    @FXML
    private Spinner<Integer> minutes_id;

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
    private Spinner<Integer> seconds_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private TextField year_id;

    @FXML
    private Label status_id;

    @FXML
    void initialize() {
        age_id.getItems().addAll(0, 6, 12, 16, 18);
        type_id.getItems().addAll("CD-ROM", "CD-R", "DVD", "BD");
        place_type_id.getItems().addAll("стойка", "полка", "ящик");
        SpinnerValueFactory hours_vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 838);
        SpinnerValueFactory minutes_vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        SpinnerValueFactory seconds_vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        hours_id.setValueFactory(hours_vf);
        minutes_id.setValueFactory(minutes_vf);
        seconds_id.setValueFactory(seconds_vf);
        place_type_id.setOnAction(actionEvent -> {
            choicePlaceType();
        });
        ok_button_id.setOnAction(actionEvent -> {
            addAudioBook();
        });
    }

    private void addAudioBook() {
        try {
            DatabaseHandler handler = new DatabaseHandler();
            int place_id = handler.getPlace(place_type_id.getValue(), place_num_id.getValue());
            String name = name_id.getText();
            String author = author_id.getText();
            String duration = hours_id.getValue() + ":" + minutes_id.getValue() + ":" + seconds_id.getValue();
            int year = Integer.parseInt(year_id.getText());
            String description = description_id.getText();
            int age_limit = age_id.getValue();
            String media_type = type_id.getValue();
            String publisher = publisher_id.getText();
            double price = Double.parseDouble(price_id.getText());
            Audiobook audiobook = new Audiobook(place_id, name, author, duration, year,
                    description, age_limit, media_type, publisher, price);
            handler.addAudiobook(audiobook);
            status_id.setText("ОК");
            status_id.setTextFill(Color.GREEN);
            status_id.setVisible(true);
            if(type_id.getValue() == null || age_id.getValue() == null || (duration.equals("0:0:0"))){
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
