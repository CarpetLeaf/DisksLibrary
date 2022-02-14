package controllers;

import java.io.IOException;
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
import sample.Audiobook;
import sample.DatabaseHandler;
import sample.Music;

public class SearchingAudiobook {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> age_id;

    @FXML
    private TextField author_id;

    @FXML
    private TableColumn<Audiobook, Integer> column_age;

    @FXML
    private TableColumn<Audiobook, String> column_author;

    @FXML
    private TableColumn<Audiobook, String> column_duration;

    @FXML
    private TableColumn<Audiobook, String> column_name;

    @FXML
    private TableColumn<Audiobook, Integer> column_place;

    @FXML
    private TableColumn<Audiobook, Double> column_price;

    @FXML
    private TableColumn<Audiobook, String> column_publisher;

    @FXML
    private TableColumn<Audiobook, String> column_type;

    @FXML
    private TableColumn<Audiobook, Integer> column_year;

    @FXML
    private TextField seconds_id;

    @FXML
    private TextField minutes_id;

    @FXML
    private TextField hours_id;

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
    private TableView<Audiobook> tableAudiobooks;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private TextField year_id;

    @FXML
    private TableColumn<Audiobook, Integer> column_id;

    @FXML
    void initialize() {
        age_id.getItems().addAll(null, 0, 6, 12, 16, 18);
        type_id.getItems().addAll("", "CD-ROM", "CD-R", "DVD", "BD");
        column_id.setCellValueFactory(new PropertyValueFactory<>("myId"));
        column_place.setCellValueFactory(new PropertyValueFactory<Audiobook, Integer>("place_id"));
        column_name.setCellValueFactory(new PropertyValueFactory<Audiobook, String>("name"));
        column_author.setCellValueFactory(new PropertyValueFactory<Audiobook, String>("author"));
        column_duration.setCellValueFactory(new PropertyValueFactory<Audiobook, String>("duration"));
        column_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        column_age.setCellValueFactory(new PropertyValueFactory<Audiobook, Integer>("age_limit"));
        column_type.setCellValueFactory(new PropertyValueFactory<Audiobook, String>("media_type"));
        column_publisher.setCellValueFactory(new PropertyValueFactory<Audiobook, String>("publisher"));
        column_price.setCellValueFactory(new PropertyValueFactory<Audiobook, Double>("price"));
        ok_button_id.setOnAction(actionEvent -> {
            if(checkFields()){
                searching();
            }
            else System.out.println("Error");
        });
        tableAudiobooks.setRowFactory( tv -> {
            TableRow<Audiobook> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Audiobook rowData = row.getItem();
                    rowData.setMyId(row.getItem().getMyId());
                    System.out.println(rowData.getMyId());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/windows/form_audiobook.fxml"));
                    try {
                        loader.load();
                    }catch (IOException ex){
                        ex.getMessage();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    FormAudiobook controller = loader.getController();
                    controller.setAudiobook(rowData);
                    stage.showAndWait();
                }
            });
            return row ;
        });
    }

    boolean checkFields(){
        boolean res = true;
        try{
            if (!(hours_id.getText() == null || hours_id.getText().trim().isEmpty()) &&
                    !(minutes_id.getText() == null || minutes_id.getText().trim().isEmpty()) &&
                    !(seconds_id.getText() == null || seconds_id.getText().trim().isEmpty())) {
                int h = Integer.parseInt(hours_id.getText().trim());
                int m = Integer.parseInt(minutes_id.getText().trim());
                int s = Integer.parseInt(seconds_id.getText().trim());
                if(h == 0 && m == 0 && s == 0)
                    return false;
                if(!(h < 839 && h >= 0) || !(m < 60 && m >= 0) || !(s < 60 && s >= 0)){
                    res = false;
                }
            }
            if (!(year_id.getText() == null || year_id.getText().trim().isEmpty())){
                Integer.parseInt(year_id.getText());
            }
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
        }catch (NumberFormatException | NullPointerException ex){
            res = false;
        }
        return res;
    }

    private String getRequest(){
        String res = "";
        if (!(name_id.getText() == null || name_id.getText().trim().isEmpty())){
            res += "name LIKE " + "\'%" + name_id.getText().trim() + "%\'";
        }
        if (!(author_id.getText() == null || author_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "author LIKE " + "\'%" + author_id.getText().trim() + "%\'";
        }
        if (!(age_id.getValue() == null || age_id.getValue().toString().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "age_limit = " + age_id.getValue().toString().trim();
        }
        if (!(hours_id.getText() == null || hours_id.getText().trim().isEmpty()) &&
                !(minutes_id.getText() == null || minutes_id.getText().trim().isEmpty()) &&
                !(seconds_id.getText() == null || seconds_id.getText().trim().isEmpty())){
            if (res != "")
                res += " AND ";
            res += "duration = " + "\'" + hours_id.getText().trim() + ":" + minutes_id.getText().trim() + ":" +
                    seconds_id.getText().trim() + "\'";
        }
        if (!(year_id.getText() == null || year_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "year = " + year_id.getText().trim();
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
            res = "SELECT * FROM audio_books WHERE " + res;
            res += ";";
        }
        else res = "SELECT * FROM audio_books;";
        return  res;
    }

    private void searching(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prepSt = handler.getConnection().prepareStatement(getRequest());
            ResultSet rs = prepSt.executeQuery();
            ObservableList<Audiobook> audiobooks = FXCollections.observableArrayList();
            Audiobook a_book;
            while(rs.next()){
                a_book = new Audiobook();
                a_book.setMyId(rs.getInt(1));
                a_book.setPlace_id(rs.getInt(2));
                a_book.setName(rs.getString(3));
                a_book.setAuthor(rs.getString(4));
                a_book.setDuration(rs.getString(5));
                a_book.setYear(rs.getInt(6));
                a_book.setDescription(rs.getString(7));
                a_book.setAge_limit(rs.getInt(8));
                a_book.setMedia_type(rs.getString(9));
                a_book.setPublisher(rs.getString(10));
                a_book.setPrice(rs.getDouble(11));
                audiobooks.add(a_book);
            }
            tableAudiobooks.setItems(audiobooks);
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
