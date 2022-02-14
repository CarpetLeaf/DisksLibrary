package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import sample.DatabaseHandler;
import sample.Film;

public class AddFilmController {

    @FXML
    private ComboBox<Integer> age_id;

    @FXML
    private TextField country_id;

    @FXML
    private TextArea description_id;

    @FXML
    private TextField director_id;

    @FXML
    private TextField duration_id;

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
    private ComboBox<String> type_id;

    @FXML
    private TextField year_id;

    @FXML
    private Label status_id;

    @FXML
    void initialize() {
        genre_id.getItems().addAll("аниме", "биографический", "боевик", "вестерн", "военный", "детектив", "детский", "документальный", "драма", "исторический", "кинокомикс", "комедия", "концерт", "короткометражный", "криминал", "мелодрама",
                "мистика", "музыка", "мультфильм", "мюзикл", "научный", "нуар", "приключения", "реалити-шоу", "семейный", "спорт", "ток-шоу", "триллер", "ужасы", "фантастика", "фэнтези", "эротика");
        age_id.getItems().addAll(0, 6, 12, 16, 18);
        type_id.getItems().addAll("CD-ROM", "CD-R", "DVD", "BD");
        place_type_id.getItems().addAll("стойка", "полка", "ящик");
        place_type_id.setOnAction(actionEvent -> {
            choicePlaceType();
        });
        ok_button_id.setOnAction(actionEvent -> {
            addFilm();
        });
    }

    private void addFilm() {
        try{
        DatabaseHandler handler = new DatabaseHandler();
        int place_id = handler.getPlace(place_type_id.getValue(), place_num_id.getValue());
        String name = name_id.getText();
        String country = country_id.getText();
        int age_limit = Integer.parseInt(age_id.getValue().toString());
        int duration = Integer.parseInt(duration_id.getText());
        String genre = genre_id.getValue();
        String director = director_id.getText();
        int released = Integer.parseInt(year_id.getText());
        String description = description_id.getText();
        String media_type = type_id.getValue();
        String publisher = publisher_id.getText();
        double price = Double.parseDouble(price_id.getText());

        Film film = new Film(place_id, name, country, age_limit, duration,
                genre, director, released, description, media_type,
                publisher, price);
        handler.addFilm(film);
        status_id.setText("ОК");
        status_id.setTextFill(Color.GREEN);
        status_id.setVisible(true);
        if(type_id.getValue() == null || genre_id.getValue() == null || age_id.getValue() == null){
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