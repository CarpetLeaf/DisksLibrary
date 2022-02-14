package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import constants.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.*;

public class Controller {

    @FXML
    private MenuItem add_audio_book;

    @FXML
    private MenuItem add_document;

    @FXML
    private MenuItem add_film;

    @FXML
    private Menu add_menu;

    @FXML
    private MenuItem add_music;

    @FXML
    private MenuItem add_program;

    @FXML
    private MenuItem find_audiobook;

    @FXML
    private MenuItem find_document;

    @FXML
    private MenuItem find_film;

    @FXML
    private MenuItem find_music;

    @FXML
    private MenuItem find_program;

    @FXML
    private MenuBar menu_id;

    @FXML
    private Menu duplicates_id;


    @FXML
    private MenuItem dup_audiobooks;

    @FXML
    private MenuItem dup_docs;

    @FXML
    private MenuItem dup_film;

    @FXML
    private MenuItem dup_music;

    @FXML
    private MenuItem dup_progs;

    @FXML
    private MenuItem filling_menu;


    @FXML
    private TableView<Place> table_filling;


    @FXML
    private TableColumn<Place, Integer> column_cnt;

    @FXML
    private TableColumn<Place, Integer> column_id;

    @FXML
    private TableColumn<Place, Integer> column_num;

    @FXML
    private TableColumn<Place, String> column_type;

    @FXML
    private TableColumn<Audiobook, Integer> acolumn_age;

    @FXML
    private TableColumn<Audiobook, String> acolumn_author;

    @FXML
    private TableColumn<Audiobook, String> acolumn_duration;

    @FXML
    private TableColumn<Audiobook, String> acolumn_name;

    @FXML
    private TableColumn<Document, Integer> acolumn_year;

    @FXML
    private TableColumn<Document, Integer> acolumn_cnt;

    @FXML
    private TableColumn<Document, String> dcolumn_date;

    @FXML
    private TableColumn<Document, String> dcolumn_name;

    @FXML
    private TableColumn<Document, Integer> dcolumn_value;

    @FXML
    private TableColumn<Document, Integer> column_price1;

    @FXML
    private TableColumn<Film, Integer> fcolumn_age;

    @FXML
    private TableColumn<Film, String> fcolumn_country;

    @FXML
    private TableColumn<Film, String> fcolumn_director;

    @FXML
    private TableColumn<Film, Integer> fcolumn_duration;

    @FXML
    private TableColumn<Film, String> fcolumn_genre;

    @FXML
    private TableColumn<Film, String> fcolumn_name;

    @FXML
    private TableColumn<Film, Double> fcolumn_cnt;

    @FXML
    private TableColumn<Film, Integer> fcolumn_year;

    @FXML
    private TableView<Document> findDocument;

    @FXML
    private TableView<Music> findMusic;

    @FXML
    private TableView<Program> findProgram;

    @FXML
    private TableColumn<Music, String> mcolumn_date;

    @FXML
    private TableColumn<Music, String> mcolumn_genre;

    @FXML
    private TableColumn<Music, String> mcolumn_name;

    @FXML
    private TableColumn<Music, Double> mcolumn_price;

    @FXML
    private TableColumn<Music, String> mcolumn_singer;

    @FXML
    private TableColumn<Program, String> pcolumn_date;

    @FXML
    private TableColumn<Program, String> pcolumn_developer;

    @FXML
    private TableColumn<Program, String> pcolumn_name;

    @FXML
    private TableColumn<Program, Double> pcolumn_price;

    @FXML
    private TableView<Audiobook> tableAudiobooks;

    @FXML
    private TableView<Film> tableFilms;

    @FXML
    void initialize() {
        add_film.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/add_film.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        add_music.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/add_music.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        add_audio_book.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/add_audiobook.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        add_document.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/add_document.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        add_program.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/add_program.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        find_film.setOnAction((actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/searching_film.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }));
        find_music.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/searching_music.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        find_audiobook.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/searching_audiobook.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        find_document.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/searching_document.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        find_program.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/searching_program.fxml"));
            try {
                loader.load();
            }catch (IOException ex){
                ex.getMessage();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        filling_menu.setOnAction(actionEvent -> {
            tableFilms.setVisible(false);
            findProgram.setVisible(false);
            findDocument.setVisible(false);
            findMusic.setVisible(false);
            tableAudiobooks.setVisible(false);
            column_id.setCellValueFactory(new PropertyValueFactory<>("myId"));
            column_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            column_num.setCellValueFactory(new PropertyValueFactory<>("num"));
            column_cnt.setCellValueFactory(new PropertyValueFactory<>("cnt"));
            table_filling.setVisible(true);
            DatabaseHandler handler = new DatabaseHandler();
            try{
                Connection connection = handler.getConnection();
                Statement statement = connection.createStatement();
                String req = "DROP VIEW IF EXISTS place_count";
                statement.executeUpdate(req);
                req = "\nCREATE VIEW place_count\n" +
                    "\tAS\n" +
                    "\tSELECT place_id, COUNT(*) AS 'count' from films GROUP BY place_id\n" +
                    "\tUNION ALL\n" +
                    "\tSELECT place_id, COUNT(*) AS 'count2' from music GROUP BY place_id\n" +
                    "\tUNION ALL\n" +
                    "\tSELECT place_id, COUNT(*) AS 'count2' from audio_books GROUP BY place_id\n" +
                    "\tUNION ALL\n" +
                    "\tSELECT place_id, COUNT(*) AS 'count2' from documents GROUP BY place_id\n" +
                    "\tUNION ALL\n" +
                    "\tSELECT place_id, COUNT(*) AS 'count2' from programms GROUP BY place_id\n";
                statement.executeUpdate(req);
                req = "\tDROP VIEW IF EXISTS cur_place_cnt;\n";
                statement.executeUpdate(req);
                req = "\tCREATE VIEW cur_place_cnt\n" +
                        "\tAS SELECT place_id, SUM(count) AS 'count' from place_count GROUP BY place_id;\n";
                statement.executeUpdate(req);
                req = "\tSELECT cur_place_cnt.place_id, places.type, places.number, cur_place_cnt.count FROM cur_place_cnt\n" +
                    "\t  JOIN places\n" +
                    "\t    ON cur_place_cnt.place_id = places.id\n" +
                    "\tORDER BY 4;";
                PreparedStatement ps = handler.getConnection().prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                ObservableList<Place> placesList = FXCollections.observableArrayList();
                Place place;
                while(rs.next()){
                    place = new Place(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                    placesList.add(place);
                }
                table_filling.setItems(placesList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        dup_film.setOnAction(actionEvent -> {
            table_filling.setVisible(false);
            tableFilms.setVisible(true);
            findProgram.setVisible(false);
            findDocument.setVisible(false);
            findMusic.setVisible(false);
            tableAudiobooks.setVisible(false);
            fcolumn_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            fcolumn_country.setCellValueFactory(new PropertyValueFactory<>("country"));
            fcolumn_age.setCellValueFactory(new PropertyValueFactory<>("age_limit"));
            fcolumn_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            fcolumn_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            fcolumn_director.setCellValueFactory(new PropertyValueFactory<>("director"));
            fcolumn_year.setCellValueFactory(new PropertyValueFactory<>("released"));
            fcolumn_cnt.setCellValueFactory(new PropertyValueFactory<>("place_id"));
            DatabaseHandler handler = new DatabaseHandler();
            String req = "SELECT\n" +
                    "\tname,\n" +
                    "\tcountry,\n" +
                    "\tage_limit,\n" +
                    "\tduration,\n" +
                    "\tgenre,\n" +
                    "\tdirector,\n" +
                    "\treleased,\t\n" +
                    "\tCOUNT(*) AS count\n" +
                    "FROM\n" +
                    "\tfilms\n" +
                    "GROUP BY\n" +
                    "\tname, country, age_limit, duration, genre, director, released\n" +
                    "HAVING \n" +
                    "\tcount > 1";
            try {
                PreparedStatement prepSt = handler.getConnection().prepareStatement(req);
                ResultSet rs = prepSt.executeQuery();
                ObservableList<Film> list = FXCollections.observableArrayList();
                Film film;
                while (rs.next()){
                    film = new Film();
                    film.setName(rs.getString(1));
                    film.setCountry(rs.getString(2));
                    film.setAge_limit(rs.getInt(3));
                    film.setDuration(rs.getInt(4));
                    film.setGenre(rs.getString(5));
                    film.setDirector(rs.getString(6));
                    film.setReleased(rs.getInt(7));
                    film.setPlace_id(rs.getInt(8));
                    list.add(film);
                }
                tableFilms.setItems(list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        dup_music.setOnAction(actionEvent -> {
            table_filling.setVisible(false);
            findMusic.setVisible(true);
            tableFilms.setVisible(false);
            findProgram.setVisible(false);
            findDocument.setVisible(false);
            tableAudiobooks.setVisible(false);
            mcolumn_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            mcolumn_singer.setCellValueFactory(new PropertyValueFactory<>("singer"));
            mcolumn_date.setCellValueFactory(new PropertyValueFactory<>("date_of_release"));
            mcolumn_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            mcolumn_price.setCellValueFactory(new PropertyValueFactory<>("place_id"));
            DatabaseHandler handler = new DatabaseHandler();
            String req = "SELECT\n" +
                    "\tname,\n" +
                    "\tsinger,\n" +
                    "\tdate_of_release,\n" +
                    "\tgenre,\n" +
                    "\tCOUNT(*) AS count\n" +
                    "FROM\n" +
                    "\tmusic\n" +
                    "GROUP BY\n" +
                    "\tname, singer, genre, date_of_release\n" +
                    "HAVING \n" +
                    "\tcount > 1";
            try {
                PreparedStatement prepSt = handler.getConnection().prepareStatement(req);
                ResultSet rs = prepSt.executeQuery();
                ObservableList<Music> list = FXCollections.observableArrayList();
                Music music;
                while (rs.next()){
                    music = new Music();
                    music.setName(rs.getString(1));
                    music.setSinger(rs.getString(2));
                    music.setDate_of_release(rs.getString(3));
                    music.setGenre(rs.getString(4));
                    music.setPlace_id(rs.getInt(5));
                    list.add(music);
                }
                findMusic.setItems(list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        dup_audiobooks.setOnAction(actionEvent -> {
            table_filling.setVisible(false);
            tableAudiobooks.setVisible(true);
            tableFilms.setVisible(false);
            findProgram.setVisible(false);
            findDocument.setVisible(false);
            findMusic.setVisible(false);
            acolumn_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            acolumn_author.setCellValueFactory(new PropertyValueFactory<>("author"));
            acolumn_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            acolumn_year.setCellValueFactory(new PropertyValueFactory<>("year"));
            acolumn_age.setCellValueFactory(new PropertyValueFactory<>("age_limit"));
            acolumn_cnt.setCellValueFactory(new PropertyValueFactory<>("place_id"));
            DatabaseHandler handler = new DatabaseHandler();
            String req = "SELECT\n" +
                    "\tname,\n" +
                    "\tauthor,\n" +
                    "\tduration,\n" +
                    "\tyear,\n" +
                    "\tage_limit,\n" +
                    "\tCOUNT(*) AS count\n" +
                    "FROM\n" +
                    "\taudio_books\n" +
                    "GROUP BY\n" +
                    "\tname, author, duration, year, age_limit\n" +
                    "HAVING \n" +
                    "\tcount > 1";
            try {
                PreparedStatement prepSt = handler.getConnection().prepareStatement(req);
                ResultSet rs = prepSt.executeQuery();
                ObservableList<Audiobook> list = FXCollections.observableArrayList();
                Audiobook audiobook;
                while (rs.next()){
                    audiobook = new Audiobook();
                    audiobook.setName(rs.getString(1));
                    audiobook.setAuthor(rs.getString(2));
                    audiobook.setDuration(rs.getString(3));
                    audiobook.setYear(rs.getInt(4));
                    audiobook.setAge_limit(rs.getInt(5));
                    audiobook.setPlace_id(rs.getInt(6));
                    list.add(audiobook);
                }
                tableAudiobooks.setItems(list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        dup_docs.setOnAction(actionEvent -> {
            table_filling.setVisible(false);
            findDocument.setVisible(true);
            tableFilms.setVisible(false);
            findProgram.setVisible(false);
            findMusic.setVisible(false);
            tableAudiobooks.setVisible(false);
            DatabaseHandler handler = new DatabaseHandler();
            dcolumn_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            dcolumn_value.setCellValueFactory(new PropertyValueFactory<>("volume"));
            dcolumn_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            column_price1.setCellValueFactory(new PropertyValueFactory<>("place_id"));
            String req = "SELECT\n" +
                    "\tname,\n" +
                    "\tvolume,\n" +
                    "\tdate,\n" +
                    "\tCOUNT(*) AS count\n" +
                    "FROM\n" +
                    "\tdocuments\n" +
                    "GROUP BY\n" +
                    "\tname, volume, date\n" +
                    "HAVING \n" +
                    "\tcount > 1";
            try {
                PreparedStatement prepSt = handler.getConnection().prepareStatement(req);
                ResultSet rs = prepSt.executeQuery();
                ObservableList<Document> list = FXCollections.observableArrayList();
                Document document;
                while (rs.next()){
                    document = new Document();
                    document.setName(rs.getString(1));
                    document.setVolume(rs.getInt(2));
                    document.setDate(rs.getString(3));
                    document.setPlace_id(rs.getInt(4));
                    list.add(document);
                }
                findDocument.setItems(list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        dup_progs.setOnAction(actionEvent -> {
            table_filling.setVisible(false);
            findProgram.setVisible(true);
            tableFilms.setVisible(false);
            findDocument.setVisible(false);
            findMusic.setVisible(false);
            tableAudiobooks.setVisible(false);
            DatabaseHandler handler = new DatabaseHandler();
            pcolumn_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            pcolumn_developer.setCellValueFactory(new PropertyValueFactory<>("developer"));
            pcolumn_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            pcolumn_price.setCellValueFactory(new PropertyValueFactory<>("place_id"));
            String req = "SELECT\n" +
                    "\tname,\n" +
                    "\tlast_update_date,\n" +
                    "\tdeveloper,\n" +
                    "\tCOUNT(*) AS count\n" +
                    "FROM\n" +
                    "\tprogramms\n" +
                    "GROUP BY\n" +
                    "\tname, last_update_date, developer\n" +
                    "HAVING \n" +
                    "\tcount > 1";
            try {
                PreparedStatement prepSt = handler.getConnection().prepareStatement(req);
                ResultSet rs = prepSt.executeQuery();
                ObservableList<Program> list = FXCollections.observableArrayList();
                Program program;
                while (rs.next()){
                    program = new Program();
                    program.setName(rs.getString(1));
                    program.setDeveloper(rs.getString(2));
                    program.setDate(rs.getString(3));
                    program.setPlace_id(rs.getInt(4));
                    list.add(program);
                }
                findProgram.setItems(list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
