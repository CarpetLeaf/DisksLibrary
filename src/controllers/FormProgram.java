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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Document;
import sample.Program;

public class FormProgram {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel_id;

    @FXML
    private DatePicker date_id;

    @FXML
    private TextArea description_id;

    @FXML
    private TextField developer_id;

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
    private Button clear_id;

    private Program prog;

    @FXML
    void initialize() {
        type_id.getItems().addAll("CD-ROM", "CD-R", "DVD", "BD");
        place_type_id.getItems().addAll("стойка", "полка", "ящик");
        place_type_id.setOnAction(actionEvent -> {
            choicePlaceType();
        });
        cancel_id.setOnAction(actionEvent -> {
            Stage stage = (Stage) cancel_id.getScene().getWindow();
            stage.close();
        });
        clear_id.setOnAction(actionEvent -> {
            date_id.setValue(null);
        });
        ok_button_id.setOnAction(actionEvent -> {
            if(checkFields()){
                DatabaseHandler handler = new DatabaseHandler();
                Program newProg = new Program();
                newProg.setMyId(prog.getMyId());
                newProg.setPlace_id(handler.getPlace(place_type_id.getValue(), place_num_id.getValue()));
                newProg.setName(name_id.getText().trim());
                newProg.setDeveloper(prog.getDeveloper());
                newProg.setDate(date_id.getValue().toString().trim());
                newProg.setDescription(prog.getDescription());
                newProg.setMedia_type(type_id.getValue());
                newProg.setPublisher(publisher_id.getText().trim());
                newProg.setPrice(Double.parseDouble(price_id.getText().trim()));
                handler.updateProgram(newProg);
            }
            else System.out.println("Error");
        });
    }

    public void setFormProgram(Program prog){
        this.prog = prog;
        name_id.setText(prog.getName());
        developer_id.setText(String.valueOf(prog.getDeveloper()));
        date_id.setValue(LocalDate.parse(prog.getDate()));
        description_id.setText(prog.getDescription());
        type_id.setValue(prog.getMedia_type());
        publisher_id.setText(prog.getPublisher());
        price_id.setText(Double.toString(prog.getPrice()));
        DatabaseHandler handler = new DatabaseHandler();
        place_type_id.setValue(handler.getTypePlace(prog.getPlace_id()));
        place_num_id.setDisable(false);
        choicePlaceType();
        place_num_id.setValue(handler.getNumPlace(prog.getPlace_id()));
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
