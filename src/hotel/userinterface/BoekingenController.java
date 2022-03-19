package hotel.userinterface;

import hotel.model.Hotel;
import hotel.model.Kamer;
import hotel.model.KamerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class BoekingenController {

    @FXML
    private TextField naamField;
    @FXML
    private TextField adresField;
    @FXML
    private DatePicker aankomstDatumPricker;
    @FXML
    private DatePicker vertrekDatumPricker;
    @FXML
    private ComboBox kamerTypeBox;
    @FXML
    private Button resetButton;
    @FXML
    private Button boekingButton;
    @FXML
    private Label errorLabel;

    private Hotel hotel = Hotel.getHotel();


    public void initialize(){
        fillKamerTypebox();

    }

    public void fillKamerTypebox(){
        ObservableList<KamerType> list = FXCollections.observableList(hotel.getKamerTypen());
        kamerTypeBox.setItems(list);
    }

    public void reset() {
        naamField.clear();
        adresField.clear();
        aankomstDatumPricker.valueProperty().set(null);
        vertrekDatumPricker.valueProperty().set(null);
        kamerTypeBox.valueProperty().set(null);
        errorLabel.setText(null);
        System.out.println("Boeking reseted.");
    }

    public void boeking() throws Exception {
        errorLabel.setText(null);
        if(aankomstDatumPricker.getValue() != null) {
            if (aankomstDatumPricker.getValue().isAfter(LocalDate.now().minusDays(1))) {
                if (aankomstDatumPricker.getValue().isBefore(vertrekDatumPricker.getValue())) {
                    if (vertrekDatumPricker.getValue() != null) {
                        if (vertrekDatumPricker.getValue().isAfter(LocalDate.now())) {
                            if (naamField.getText() != null) {
                                if (adresField.getText() != null) {
                                    if (kamerTypeBox.getValue() != null) {
                                        hotel.voegBoekingToe(aankomstDatumPricker.getValue(), vertrekDatumPricker.getValue(), naamField.getText(), adresField.getText(), (KamerType) kamerTypeBox.getValue());
                                        System.out.println("Boeking Geslaagt!");
                                    }else
                                        errorLabel.setText("Oops.. er is iets fout gegaan.\n Een/meerdere van de velden zijn niet ingevuld.");
                                }else
                                    errorLabel.setText("Oops.. er is iets fout gegaan.\n Een/meerdere van de velden zijn niet ingevuld.");
                            }else
                                errorLabel.setText("Oops.. er is iets fout gegaan.\n Een/meerdere van de velden zijn niet ingevuld.");
                        } else
                            errorLabel.setText("Oops.. er is iets fout gegaan.\n Vertrekdatum mag niet in het verleden geplaatst worden.");
                    }else
                        errorLabel.setText("Oops.. er is iets fout gegaan.\n Een/meerdere van de velden zijn niet ingevuld.");
                } else
                    errorLabel.setText("Oops.. er is iets fout gegaan.\n Aankomstdatum kan niet na vertrekdatum zijn.");
            } else
                errorLabel.setText("Oops.. er is iets fout gegaan.\n Aankomstdatum mag niet in het verleden geplaatst worden.");
        } else
            errorLabel.setText("Oops.. er is iets fout gegaan.\n Een/meerdere van de velden zijn niet ingevuld.");
    }
}
