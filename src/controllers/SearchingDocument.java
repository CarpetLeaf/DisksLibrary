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
import sample.Audiobook;
import sample.DatabaseHandler;
import sample.Document;

public class SearchingDocument {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clear_id;

    @FXML
    private TableColumn<Document, Date> column_date;

    @FXML
    private TableColumn<Document, String> column_name;

    @FXML
    private TableColumn<Document, Integer> column_place;

    @FXML
    private TableColumn<Document, Double> column_price;

    @FXML
    private TableColumn<Document, String> column_publisher;

    @FXML
    private TableColumn<Document, String> column_type;

    @FXML
    private TableColumn<Document, Integer> column_value;

    @FXML
    private DatePicker date_id;

    @FXML
    private TableView<Document> findDocument;

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
    private TextField value_max_id;

    @FXML
    private TextField value_min_id;

    @FXML
    private TableColumn<Document, Integer> column_id;

    @FXML
    void initialize() {
        type_id.getItems().addAll("", "CD-ROM", "CD-R", "DVD", "BD");
        date_id.setEditable(false);
        column_id.setCellValueFactory(new PropertyValueFactory<>("myId"));
        column_place.setCellValueFactory(new PropertyValueFactory<>("place_id"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_value.setCellValueFactory(new PropertyValueFactory<>("volume"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
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
        findDocument.setRowFactory( tv -> {
            TableRow<Document> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Document rowData = row.getItem();
                    rowData.setMyId(row.getItem().getMyId());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/windows/form_document.fxml"));
                    try {
                        loader.load();
                    }catch (IOException ex){
                        ex.getMessage();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    FormDocument controller = loader.getController();
                    controller.setFormDocument(rowData);
                    stage.showAndWait();
                }
            });
            return row ;
        });
    }

    private boolean checkFields(){
        boolean res = true;
        try{
            if(!(date_id.getValue() == null || date_id.getValue().toString().trim().isEmpty()))
                Date.valueOf(date_id.getValue().toString());
            if(!(value_min_id.getText() == null || value_min_id.getText().trim().isEmpty())) {
                int x = Integer.parseInt(value_min_id.getText().trim());
                if(x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
            }
            if(!(value_max_id.getText() == null || value_max_id.getText().trim().isEmpty())) {
                int x = Integer.parseInt(value_max_id.getText().trim());
                if(x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
            }
            if(!(value_min_id.getText() == null || value_min_id.getText().trim().isEmpty()) &&
                    !(value_max_id.getText() == null || value_max_id.getText().trim().isEmpty())){
                int min = Integer.parseInt(value_min_id.getText().trim());
                int max = Integer.parseInt(value_max_id.getText().trim());
                if(min > max)
                    return false;
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
        }catch (IllegalArgumentException | NullPointerException ex){
            res = false;
        }
        return res;
    }

    private String getRequest(){
        String res = "";
        if (!(name_id.getText() == null || name_id.getText().trim().isEmpty())){
            res += "name LIKE " + "\'%" + name_id.getText().trim() + "%\'";
        }
        if (!(value_min_id.getText() == null || value_min_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "volume >= " + "\'" + value_min_id.getText().trim() + "\'";
        }
        if (!(value_max_id.getText() == null || value_max_id.getText().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "volume <= " + "\'" + value_max_id.getText().trim() + "\'";
        }
        if (!(date_id.getValue() == null || date_id.getValue().toString().trim().isEmpty())){
            if(res != "")
                res += " AND ";
            res += "date = " + "\'" + date_id.getValue().toString().trim() + "\'";
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
            res = "SELECT * FROM documents WHERE " + res;
            res += ";";
        }
        else res = "SELECT * FROM documents;";
        return  res;
    }

    private void searching(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prepSt = handler.getConnection().prepareStatement(getRequest());
            ResultSet rs = prepSt.executeQuery();
            ObservableList<Document> docList = FXCollections.observableArrayList();
            Document doc;
            while(rs.next()){
                doc = new Document();
                doc.setMyId(rs.getInt(1));
                doc.setPlace_id(rs.getInt(2));
                doc.setName(rs.getString(3));
                doc.setVolume(rs.getInt(4));
                doc.setDate(rs.getString(5));
                doc.setMedia_type(rs.getString(6));
                doc.setPublisher(rs.getString(7));
                doc.setPrice(rs.getDouble(8));
                docList.add(doc);
            }
            findDocument.setItems(docList);
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
