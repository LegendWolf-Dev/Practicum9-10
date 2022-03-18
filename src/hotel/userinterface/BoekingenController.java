package hotel.userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
}
