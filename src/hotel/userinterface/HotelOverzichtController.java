package hotel.userinterface;

import hotel.HotelApp;
import hotel.model.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class HotelOverzichtController {
    @FXML private Label hotelnaamLabel;
    @FXML private ListView boekingenListView;
    @FXML private DatePicker overzichtDatePicker;

    private Hotel hotel = Hotel.getHotel();

    public void initialize() {
        hotelnaamLabel.setText("Boekingen hotel " + hotel.getNaam());
        overzichtDatePicker.setValue(LocalDate.now());
        toonBoekingen();
    }

    public void toonVorigeDag(ActionEvent actionEvent) {
        LocalDate dagEerder = overzichtDatePicker.getValue().minusDays(1);
        overzichtDatePicker.setValue(dagEerder);
    }

    public void toonVolgendeDag(ActionEvent actionEvent) {
        LocalDate dagLater = overzichtDatePicker.getValue().plusDays(1);
        overzichtDatePicker.setValue(dagLater);
    }

    public void nieuweBoeking(ActionEvent actionEvent) {
        //System.out.println("nieuweBoeking() is nog niet geïmplementeerd!");
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Boekingen.fxml"));
        Parent root = loader.load();
        Stage boeking = new Stage();
        boeking.setTitle("Boekingen");
        boeking.setScene(new Scene(root,469.0,382.0));
        boeking.show();
        boeking.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                toonBoekingen();
            }
        });

        }catch (Exception e){
            e.printStackTrace();
        }

        // Maak in je project een nieuwe FXML-pagina om boekingen te kunnen invoeren
        // Open de nieuwe pagina in deze methode
        // Zorg dat de gebruiker ondertussen geen gebruik kan maken van de HotelOverzicht-pagina
        // Update na sluiten van de nieuwe pagina het boekingen-overzicht
    }

    public void toonBoekingen() {
        String txt = "";
        ObservableList<String> boekingen = FXCollections.observableArrayList();
        for (int i = 0; i < hotel.getBoekingen().size(); i++) {
            if(hotel.getBoekingen().get(i).getAankomstDatum().isEqual((overzichtDatePicker.getValue()))){

                txt += hotel.getBoekingen().get(i).getAankomstDatum() + " - ";
                txt += hotel.getBoekingen().get(i).getVertrekDatum() + " - ";
                txt += hotel.getBoekingen().get(i).getKamer() + " - ";
                txt += hotel.getBoekingen().get(i).getBoeker().getNaam() + "\n";
                boekingen.add(txt);
                txt = "";}

        }




        // Vraag de boekingen op bij het Hotel-object.
        // Voeg voor elke boeking in nette tekst (string) toe aan de boekingen-lijst.

        boekingenListView.setItems(boekingen);
    }
}