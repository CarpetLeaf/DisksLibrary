package controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import constants.Const;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Film;

public class FormFilm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Label status_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private TextField year_id;

    @FXML
    private Button close_id;

    private Film film;

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
        close_id.setOnAction(actionEvent -> {
            Stage stage = (Stage) close_id.getScene().getWindow();
            stage.close();
        });
        ok_button_id.setOnAction(actionEvent -> {
            if(checkFields()){
                DatabaseHandler handler = new DatabaseHandler();
                Film newFilm = new Film();
                System.out.println(film.getMyId());
                newFilm.setMyId(film.getMyId());
                newFilm.setPlace_id(handler.getPlace(place_type_id.getValue(), place_num_id.getValue()));
                newFilm.setName(name_id.getText().trim());
                newFilm.setCountry(country_id.getText().trim());
                newFilm.setAge_limit(age_id.getValue());
                newFilm.setDuration(Integer.parseInt(duration_id.getText().trim()));
                newFilm.setGenre(genre_id.getValue());
                newFilm.setDirector(director_id.getText().trim());
                newFilm.setReleased(Integer.parseInt(year_id.getText().trim()));
                newFilm.setMedia_type(type_id.getValue());
                newFilm.setPublisher(publisher_id.getText().trim());
                newFilm.setPrice(Double.parseDouble(price_id.getText().trim()));
                newFilm.setDescription(description_id.getText());
                handler.updateFilm(newFilm);
            }
            else System.out.println("Error");
        });
    }

    public void setFormFilm(Film film){
        this.film = film;
        name_id.setText(film.getName());
        country_id.setText(film.getCountry());
        age_id.setValue(film.getAge_limit());
        duration_id.setText(Integer.toString(film.getDuration()));
        genre_id.setValue(film.getGenre());
        director_id.setText(film.getDirector());
        year_id.setText(Integer.toString(film.getReleased()));
        type_id.setValue(film.getMedia_type());
        publisher_id.setText(film.getPublisher());
        price_id.setText(Double.toString(film.getPrice()));
        DatabaseHandler handler = new DatabaseHandler();
        place_type_id.setValue(handler.getTypePlace(film.getPlace_id()));
        place_num_id.setDisable(false);
        choicePlaceType();
        place_num_id.setValue(handler.getNumPlace(film.getPlace_id()));
        description_id.setText(film.getDescription());
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
            if (!(duration_id.getText() == null || duration_id.getText().trim().isEmpty())){
                Integer.parseInt(duration_id.getText());
            }
            if (!(year_id.getText() == null || year_id.getText().trim().isEmpty())){
                Integer.parseInt(year_id.getText());
            }
            if (!(price_id.getText() == null || price_id.getText().trim().isEmpty())){
                Double.parseDouble(price_id.getText());
                double x = Double.parseDouble(price_id.getText());
                if(x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
                String[] k = String.valueOf(x).split("\\.");
                if (k[1].length()>2)
                    return false;
            }
        }catch (NumberFormatException | NullPointerException ex){
            res = false;
        }
        return res;
    }

}
