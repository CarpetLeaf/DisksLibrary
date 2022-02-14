package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import sample.Music;

public class SearchingMusic {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Music, String> column_date;

    @FXML
    private TableColumn<Music, String> column_format;

    @FXML
    private TableColumn<Music, String> column_genre;

    @FXML
    private TableColumn<Music, String> column_name;

    @FXML
    private TableColumn<Music, Integer> column_place;

    @FXML
    private TableColumn<Music, Double> column_price;

    @FXML
    private TableColumn<Music, String> column_publisher;

    @FXML
    private TableColumn<Music, String> column_singer;

    @FXML
    private TableColumn<Music, String> column_type;

    @FXML
    private TableView<Music> findMusic;

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
    private TextField price_min_id;

    @FXML
    private TextField price_max_id;

    @FXML
    private TextField publisher_id;

    @FXML
    private TextField singer_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private Button clear_id;

    @FXML
    private TableColumn<Music, Integer> column_id;

    @FXML
    void initialize() {
        genre_id.getItems().addAll("", "Народная музыка", "Духованя музыка", "Академическая музыка", "Фолк", "Кантри", "Латиноамериканская музыка", "Блюз", "Ритм-н-блюз", "Джаз", "Шансон", "Романс", "Авторская песня",
                "Электронная музыка", "Рок", "Хип-хоп", "Регги", "Поп-музыка");
        format_id.getItems().addAll("", "common", "mp3");
        type_id.getItems().addAll("", "CD-ROM", "CD-R", "DVD", "BD");
        date_id.setEditable(false);
        column_id.setCellValueFactory(new PropertyValueFactory<>("myId"));
        column_place.setCellValueFactory(new PropertyValueFactory<>("place_id"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_singer.setCellValueFactory(new PropertyValueFactory<>("singer"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("date_of_release"));
        column_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        column_format.setCellValueFactory(new PropertyValueFactory<>("format"));
        column_type.setCellValueFactory(new PropertyValueFactory<>("media_type"));
        column_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        column_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ok_button_id.setOnAction(actionEvent -> {
            if (checkFields())
                searching();
            else System.out.println("Error");
        });
        clear_id.setOnAction(actionEvent -> {
            date_id.setValue(null);
        });
        findMusic.setRowFactory( tv -> {
            TableRow<Music> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Music rowData = row.getItem();
                    rowData.setMyId(row.getItem().getMyId());
                    System.out.println(rowData.getMyId());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/windows/form_music.fxml"));
                    try {
                        loader.load();
                    }catch (IOException ex){
                        ex.getMessage();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    FormMusic controller = loader.getController();
                    controller.setFormMusic(rowData);
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
        if (!(singer_id.getText() == null || singer_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "singer LIKE " + "\'%" + singer_id.getText().trim() + "%\'";
        }
        if (!(date_id.getValue() == null || date_id.getValue().toString().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "date_of_release = " + "\'" + date_id.getValue().toString().trim() + "\'";
        }
        if (!(genre_id.getValue() == null || genre_id.getValue().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "genre = " + "\'" + genre_id.getValue().trim() + "\'";
        }
        if (!(format_id.getValue() == null || format_id.getValue().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "format = " + "\'" + format_id.getValue().trim() + "\'";
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
            res = "SELECT * FROM music WHERE " + res;
            res += ";";
        }
        else res = "SELECT * FROM music;";
        return  res;
    }

    private boolean checkFields(){
        boolean res = true;
        try{
            if(!(date_id.getValue() == null || date_id.getValue().toString().trim().isEmpty()))
                Date.valueOf(date_id.getValue().toString());
            if (!(price_min_id.getText() == null || price_min_id.getText().trim().isEmpty())){
                Double.parseDouble(price_min_id.getText());
                double x = Double.parseDouble(price_min_id.getText());
                if(x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
                String[] k = String.valueOf(x).split("\\.");
                if (k[1].length()>2)
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
        }catch (IllegalArgumentException | NullPointerException ex){
            res = false;
        }
        return res;
    }

    void searching(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prepSt = handler.getConnection().prepareStatement(getRequest());
            ResultSet rs = prepSt.executeQuery();
            ObservableList<Music> musicList = FXCollections.observableArrayList();
            Music music;
            while(rs.next()){
                music = new Music();
                music.setMyId(rs.getInt(1));
                music.setPlace_id(rs.getInt(2));
                music.setName(rs.getString(3));
                music.setSinger(rs.getString(4));
                music.setDate_of_release(rs.getString(5));
                music.setGenre(rs.getString(6));
                music.setFormat(rs.getString(7));
                music.setMedia_type(rs.getString(8));
                music.setPublisher(rs.getString(9));
                music.setPrice(rs.getDouble(10));
                musicList.add(music);
            }
            findMusic.setItems(musicList);
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
