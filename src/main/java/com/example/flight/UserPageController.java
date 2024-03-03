package com.example.flight;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Flight;

import java.io.IOException;
import java.util.Objects;

public class UserPageController {

    @FXML
    private TableView<Flight> flightTable;

    @FXML
    private TableColumn<Flight, String> flightNumberColumn;

    @FXML
    private TableColumn<Flight, String> departureLocationColumn;

    @FXML
    private TableColumn<Flight, String> destinationColumn;

    @FXML
    private TableColumn<Flight, String> dateColumn;

    @FXML
    private TableColumn<Flight, String> departureTimeColumn;

    @FXML
    private TableColumn<Flight, String> arrivalTimeColumn;

    @FXML
    private TableColumn<Flight, Integer> totalSeatsColumn;

    @FXML
    private TableColumn<Flight, Double> priceColumn;

    @FXML
    private TextField filter_from;

    @FXML
    private TextField filter_to;



    public void initialize() {
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
        departureLocationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureLocation"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("date"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
        totalSeatsColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("totalSeats"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight, Double>("price"));

        ObservableList<Flight> flightsObservableList = FXCollections.observableArrayList(flight.getFlights());
        FilteredList<Flight> filteredFlight = new FilteredList<>(flightsObservableList, b -> true);

        filter_from.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFlight.setPredicate(flight -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String search = newValue.toLowerCase();

                return flight.getDepartureLocation().toLowerCase().contains(search) &&
                        (filter_to.getText().isEmpty() || flight.getDestination().toLowerCase().contains(filter_to.getText().toLowerCase()));
            });
        });

        filter_to.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFlight.setPredicate(flight -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String search = newValue.toLowerCase();

                return flight.getDestination().toLowerCase().contains(search) &&
                        (filter_from.getText().isEmpty() || flight.getDepartureLocation().toLowerCase().contains(filter_from.getText().toLowerCase()));
            });
        });

        flightTable.setItems(filteredFlight);
    }

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("front.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("*************** Flight Reservation ********************");
        stage.setScene(scene);
        stage.show();
    }

    public void goToReserve(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("reserve_flight.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("*************** Flight Reservation ********************");
        stage.setScene(scene);
        stage.show();
    }



}
