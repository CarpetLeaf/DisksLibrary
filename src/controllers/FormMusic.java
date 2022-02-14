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
import sample.Film;
import sample.Music;

public class FormMusic {

    Music music;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date_id;

    @FXML
    private ComboBox<String> format_id;

    @FXML
    private ComboBox<String> genre_id;

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
    private TextField singer_id;

    @FXML
    private Label status_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private Button cancel_id;

    @FXML
    void initialize() {
        genre_id.getItems().addAll("Народная музыка", "Духованя музыка", "Академическая музыка", "Фолк", "Кантри", "Латиноамериканская музыка", "Блюз", "Ритм-н-блюз", "Джаз", "Шансон", "Романс", "Авторская песня",
                "Электронная музыка", "Рок", "Хип-хоп", "Регги", "Поп-музыка");
        format_id.getItems().addAll("common", "mp3");
        type_id.getItems().addAll("CD-ROM", "CD-R", "DVD", "BD");
        place_type_id.getItems().addAll("стойка", "полка", "ящик");
        place_type_id.setOnAction(actionEvent -> {
            choicePlaceType();
        });
        cancel_id.setOnAction(actionEvent -> {
            Stage stage = (Stage) cancel_id.getScene().getWindow();
            stage.close();
        });
        ok_button_id.setOnAction(actionEvent -> {
            if(checkFields()){
                DatabaseHandler handler = new DatabaseHandler();
                Music newMusic = new Music();
                newMusic.setMyId(music.getMyId());
                newMusic.setPlace_id(handler.getPlace(place_type_id.getValue(), place_num_id.getValue()));
                newMusic.setName(name_id.getText().trim());
                newMusic.setSinger(singer_id.getText().trim());
                newMusic.setDate_of_release(date_id.getValue().toString().trim());
                newMusic.setGenre(genre_id.getValue());
                newMusic.setFormat(format_id.getValue().trim());
                newMusic.setMedia_type(type_id.getValue());
                newMusic.setPublisher(publisher_id.getText().trim());
                newMusic.setPrice(Double.parseDouble(price_id.getText().trim()));
                handler.updateMusic(newMusic);
            }
            else System.out.println("Error");
        });
    }

    public void setFormMusic(Music music){
        this.music = music;
        name_id.setText(music.getName());
        singer_id.setText(music.getSinger());
        date_id.setValue(LocalDate.parse(music.getDate_of_release()));
        genre_id.setValue(music.getGenre());
        format_id.setValue(music.getFormat());
        type_id.setValue(music.getMedia_type());
        publisher_id.setText(music.getPublisher());
        price_id.setText(Double.toString(music.getPrice()));
        DatabaseHandler handler = new DatabaseHandler();
        place_type_id.setValue(handler.getTypePlace(music.getPlace_id()));
        place_num_id.setDisable(false);
        choicePlaceType();
        place_num_id.setValue(handler.getNumPlace(music.getPlace_id()));
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
