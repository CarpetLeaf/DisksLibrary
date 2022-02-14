package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import sample.DatabaseHandler;
import sample.Film;
import sample.Music;

public class AddMusicController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date_id = new DatePicker(LocalDate.now());

    @FXML
    private ComboBox<String> genre_id;

    @FXML
    private ComboBox<String> format_id;

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
    private ComboBox<String> type_id;

    @FXML
    private Label status_id;

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
        ok_button_id.setOnAction(actionEvent -> {
            addMusic();
        });
    }

    private void addMusic() {
        try {
            DatabaseHandler handler = new DatabaseHandler();
            int place_id = handler.getPlace(place_type_id.getValue(), place_num_id.getValue());
            String name = name_id.getText();
            String singer = singer_id.getText();
            String date_of_release = date_id.getValue().toString();
            String genre = genre_id.getValue();
            String format = format_id.getValue();
            String media_type = type_id.getValue();
            String publisher = publisher_id.getText();
            double price = Double.parseDouble(price_id.getText());
            Music music = new Music(place_id, name, singer, date_of_release, genre,
                    format, media_type, publisher, price);
            handler.addMusic(music);
            status_id.setText("ОК");
            status_id.setTextFill(Color.GREEN);
            status_id.setVisible(true);
            if(type_id.getValue() == null || genre_id.getValue() == null || format_id.getValue() == null){
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
