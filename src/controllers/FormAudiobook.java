package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Audiobook;
import sample.DatabaseHandler;
import sample.Music;

public class FormAudiobook {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> age_id;

    @FXML
    private TextField author_id;

    @FXML
    private TextArea description_id;

    @FXML
    private Spinner<Integer> hours_id;

    @FXML
    private Spinner<Integer> minutes_id;

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
    private Spinner<Integer> seconds_id;

    @FXML
    private Label status_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private TextField year_id;

    @FXML
    private Button cancel_id;

    private Audiobook audio;

    @FXML
    void initialize() {
        age_id.getItems().addAll(0, 6, 12, 16, 18);
        type_id.getItems().addAll("CD-ROM", "CD-R", "DVD", "BD");
        place_type_id.getItems().addAll("стойка", "полка", "ящик");
        SpinnerValueFactory hours_vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 838);
        SpinnerValueFactory minutes_vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        SpinnerValueFactory seconds_vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        hours_id.setValueFactory(hours_vf);
        minutes_id.setValueFactory(minutes_vf);
        seconds_id.setValueFactory(seconds_vf);
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
                Audiobook newAudio = new Audiobook();
                newAudio.setMyId(audio.getMyId());
                newAudio.setPlace_id(handler.getPlace(place_type_id.getValue(), place_num_id.getValue()));
                newAudio.setName(name_id.getText().trim());
                newAudio.setAuthor(author_id.getText().trim());
                newAudio.setDuration(hours_id.getValue() + ":" + minutes_id.getValue() + ":" + seconds_id.getValue());
                newAudio.setYear(Integer.parseInt(year_id.getText().trim()));
                newAudio.setDescription(description_id.getText());
                newAudio.setAge_limit(age_id.getValue());
                newAudio.setMedia_type(type_id.getValue());
                newAudio.setPublisher(publisher_id.getText().trim());
                newAudio.setPrice(Double.parseDouble(price_id.getText().trim()));
                handler.updateAudiobook(newAudio);
            }
            else System.out.println("Error");
        });
    }

    private void choicePlaceType(){
        place_num_id.getItems().clear();
        DatabaseHandler handler = new DatabaseHandler();
        place_num_id.getItems().addAll(handler.getAllNumbers(place_type_id.getValue()));
        place_num_id.setDisable(false);
    }

    boolean checkFields(){
        boolean res = true;
        try{
            if (!(hours_id.getValue() == null || hours_id.getValue().toString().trim().isEmpty()) &&
                    !(minutes_id.getValue() == null || minutes_id.getValue().toString().trim().isEmpty()) &&
                    !(seconds_id.getValue() == null || seconds_id.getValue().toString().trim().isEmpty())) {
                int h = hours_id.getValue();
                int m = minutes_id.getValue();
                int s = seconds_id.getValue();
                if(h == 0 && m == 0 && s == 0)
                    return false;
                if(!(h < 839 && h >= 0) ||  !(m < 60 && m >= 0) || !(s <60 && s >= 0)){
                    res = false;
                }
            }
            if (!(year_id.getText() == null || year_id.getText().trim().isEmpty())){
                Integer.parseInt(year_id.getText());
            }
            if (!(price_id.getText() == null || price_id.getText().trim().isEmpty())) {
                Double.parseDouble(price_id.getText());
                double x = Double.parseDouble(price_id.getText());
                if (x < 0 || x > Math.pow(10, 9) - 1)
                    return false;
                String[] k = String.valueOf(x).split("\\.");
                if (k[1].length() > 2)
                    return false;
            }
        }catch (NumberFormatException | NullPointerException ex){
            res = false;
        }
        return res;
    }

    public void setAudiobook(Audiobook audio){
        this.audio = audio;
        name_id.setText(audio.getName());
        author_id.setText(audio.getAuthor());
        String[] time = String.valueOf(audio.getDuration()).split("\\:");
        hours_id.getValueFactory().setValue(Integer.parseInt(time[0]));
        minutes_id.getValueFactory().setValue(Integer.parseInt(time[1]));
        seconds_id.getValueFactory().setValue(Integer.parseInt(time[2]));
        year_id.setText(String.valueOf(audio.getYear()));
        description_id.setText(audio.getDescription());
        age_id.setValue(audio.getAge_limit());
        type_id.setValue(audio.getMedia_type());
        publisher_id.setText(audio.getPublisher());
        price_id.setText(Double.toString(audio.getPrice()));
        DatabaseHandler handler = new DatabaseHandler();
        place_type_id.setValue(handler.getTypePlace(audio.getPlace_id()));
        place_num_id.setDisable(false);
        choicePlaceType();
        place_num_id.setValue(handler.getNumPlace(audio.getPlace_id()));
    }

}
