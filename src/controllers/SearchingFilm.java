package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Film;

public class SearchingFilm {

    @FXML
    private TableView<Film> tableFilms;

    @FXML
    private TableColumn<Film, Integer> column_age;

    @FXML
    private TableColumn<Film, String> column_country;

    @FXML
    private TableColumn<Film, String> column_director;

    @FXML
    private TableColumn<Film, Integer> column_duration;

    @FXML
    private TableColumn<Film, String> column_genre;

    @FXML
    private TableColumn<Film, String> column_name;

    @FXML
    private TableColumn<Film, Integer> column_place;

    @FXML
    private TableColumn<Film, Double> column_price;

    @FXML
    private TableColumn<Film, String> column_publisher;

    @FXML
    private TableColumn<Film, String> column_type;

    @FXML
    private TableColumn<Film, Integer> column_year;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> age_id;

    @FXML
    private TextField country_id;

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
    private TextField price_min_id;

    @FXML
    private TextField price_max_id;

    @FXML
    private TextField publisher_id;

    @FXML
    private Label status_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private TextField year_id;

    @FXML
    private TableColumn<?, ?> column_id;

    @FXML
    void initialize() {
        genre_id.getItems().addAll("", "аниме", "биографический", "боевик", "вестерн", "военный", "детектив", "детский", "документальный", "драма", "исторический", "кинокомикс", "комедия", "концерт", "короткометражный", "криминал", "мелодрама",
                "мистика", "музыка", "мультфильм", "мюзикл", "научный", "нуар", "приключения", "реалити-шоу", "семейный", "спорт", "ток-шоу", "триллер", "ужасы", "фантастика", "фэнтези", "эротика");
        age_id.getItems().addAll(null, 0, 6, 12, 16, 18);
        type_id.getItems().addAll("", "CD-ROM", "CD-R", "DVD", "BD");
        column_id.setCellValueFactory(new PropertyValueFactory<>("myId"));
        column_place.setCellValueFactory(new PropertyValueFactory<Film, Integer>("place_id"));
        column_name.setCellValueFactory(new PropertyValueFactory<Film, String>("name"));
        column_country.setCellValueFactory(new PropertyValueFactory<Film, String>("country"));
        column_age.setCellValueFactory(new PropertyValueFactory<Film, Integer>("age_limit"));
        column_duration.setCellValueFactory(new PropertyValueFactory<Film, Integer>("duration"));
        column_genre.setCellValueFactory(new PropertyValueFactory<Film, String>("genre"));
        column_director.setCellValueFactory(new PropertyValueFactory<Film, String>("director"));
        column_year.setCellValueFactory(new PropertyValueFactory<>("released"));
        column_type.setCellValueFactory(new PropertyValueFactory<Film, String>("media_type"));
        column_publisher.setCellValueFactory(new PropertyValueFactory<Film, String>("publisher"));
        column_price.setCellValueFactory(new PropertyValueFactory<Film, Double>("price"));
        ok_button_id.setOnAction(actionEvent -> {
            if(checkFields()){
                searching();
            }
            else System.out.println("Error");
        });
        tableFilms.setRowFactory( tv -> {
            TableRow<Film> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Film rowData = row.getItem();
                    rowData.setMyId(row.getItem().getMyId());
                    System.out.println(rowData.getMyId());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/windows/form_film.fxml"));
                    try {
                        loader.load();
                    }catch (IOException ex){
                        ex.getMessage();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    FormFilm controller = loader.getController();
                    controller.setFormFilm(rowData);
                    stage.showAndWait();
                }
            });
            return row ;
        });
    }

    private String getRequest(){
        String res = "";
        if (!(name_id.getText() == null || name_id.getText().trim().isEmpty())){
            res += "name LIKE " + "\'%" + name_id.getText().trim() + "%\'";
        }
        if (!(country_id.getText() == null || country_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "country = " + "\'" + country_id.getText().trim() + "\'";
        }
        if (!(age_id.getValue() == null || age_id.getValue().toString().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "age_limit = " + age_id.getValue().toString().trim();
        }
        if (!(duration_id.getText() == null || duration_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "duration = " + duration_id.getText().trim();
        }
        if (!(genre_id.getValue() == null || genre_id.getValue().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "genre = " + "\'" + genre_id.getValue().trim() + "\'";
        }
        if (!(director_id.getText() == null || director_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "director LIKE " + "\'%" + director_id.getText().trim() + "%\'";
        }
        if (!(year_id.getText() == null || year_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "released = " + year_id.getText().trim();
        }
        if (!(type_id.getValue() == null || type_id.getValue().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "media_type = " + "\'" + type_id.getValue().trim() + "\'";
        }
        if (!(publisher_id.getText() == null || publisher_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "publisher LIKE " + "\'%" + publisher_id.getText().trim() + "%\'";
        }
        if (!(price_min_id.getText() == null || price_min_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "price >= " + price_min_id.getText().trim();
        }
        if (!(price_max_id.getText() == null || price_max_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "price <= " + price_max_id.getText().trim();
        }
        if(res != "") {
            res = "SELECT * FROM films WHERE " + res;
            res += ";";
        }
        else{
            res = "SELECT * FROM films;";
        }
        return  res;
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
            if (!(price_min_id.getText() == null || price_min_id.getText().trim().isEmpty())){
                Double.parseDouble(price_min_id.getText());
                double x = Double.parseDouble(price_min_id.getText());
                if(x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
                int k =0;
                while(x > (int)x){
                    x *= 10;
                    k++;
                }
                if(k > 2)
                    return false;
            }
            if (!(price_max_id.getText() == null || price_max_id.getText().trim().isEmpty())){
                Double.parseDouble(price_max_id.getText());
                double x = Double.parseDouble(price_max_id.getText());
                if(x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
                String[] k = String.valueOf(x).split("\\.");
                if (k[1].length()>2)
                    return false;
            }
            if(!(price_min_id.getText() == null || price_min_id.getText().trim().isEmpty()) &&
                    !(price_max_id.getText() == null || price_max_id.getText().trim().isEmpty())){
                double min = Double.parseDouble(price_min_id.getText());
                double max = Double.parseDouble(price_max_id.getText());
                if (max < min)
                    return false;
            }
        }catch (NumberFormatException | NullPointerException ex){
            res = false;
        }
        return res;
    }

    private void searching(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prepSt = handler.getConnection().prepareStatement(getRequest());
            ResultSet rs = prepSt.executeQuery();
            ObservableList<Film> films = FXCollections.observableArrayList();
            Film film;
            while(rs.next()){
                film = new Film();
                film.setMyId(rs.getInt(1));
                film.setPlace_id(rs.getInt(2));
                film.setName(rs.getString(3));
                film.setCountry(rs.getString(4));
                film.setAge_limit(rs.getInt(5));
                film.setDuration(rs.getInt(6));
                film.setGenre(rs.getString(7));
                film.setDirector(rs.getString(8));
                film.setReleased(rs.getInt(9));
                film.setDescription(rs.getString(10));
                film.setMedia_type(rs.getString(11));
                film.setPublisher(rs.getString(12));
                film.setPrice(rs.getDouble(13));
                films.add(film);
            }
            tableFilms.setItems(films);
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
