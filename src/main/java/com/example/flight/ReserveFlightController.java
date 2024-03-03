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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Airplane;
import models.Flight;
import models.Passenger;
import models.Reservation;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.util.Objects;

import java.io.IOException;
import java.util.Objects;

public class ReserveFlightController {

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

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private TextField email;

    @FXML
    private TextField phone_number;

    @FXML
    private TextField flight_number;

    @FXML
    private TextField seat_number;

    @FXML
    private TextArea available_seats;

    @FXML
    private Label message;

    @FXML
    private Label reservation_id;


    String flightNumber;
    int seatNumber;

    public void initialize() {
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
        departureLocationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureLocation"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("date"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
        totalSeatsColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("totalSeats"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight, Double>("price"));
        reservation_id.setText(String.valueOf(flight.getReservationId()));
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

        flightTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                message.setText("");
                flightNumber = newSelection.getFlightNumber();
                String seatNumberText = seat_number.getText().trim();
                if (!seatNumberText.isEmpty() && !seatNumberText.isBlank()) {
                    seatNumber = Integer.parseInt(seatNumberText);
                }

                flight_number.setText(flightNumber);
                displayAvailableSeats(flightNumber);
            }

        });
    }

    public boolean isSeatAvailable(String flightNumber, int seat){
        boolean check = false;
        for (Flight flights: flight.getFlights()){
            if(flights.getFlightNumber().equals(flightNumber)){
                boolean[] availableSeats = flights.getAirplane().getSeats();
                check =  availableSeats[seat-1];
                break;
            }
        }
        if (check) return true;
        else {
            message.setText("Seat not Available!");
            message.setTextFill(Color.RED);
            return false;
        }
    }
    public void reserve (String flightNumber , int seatNumber){
        if (flightNumber != null || !flightNumber.isEmpty()){
        flight.getReservations().add(new Reservation(flight.getReservationId() , flightNumber , flight.getPassengerId() , seatNumber));
        for( Flight flight : flight.getFlights()){
            if(flight.getFlightNumber().equals(flightNumber)){
                flight.getAirplane().reserveAirplaneSeat(seatNumber);
                break;
            }
        }
        }
    }

    public void reserveFlight() {
        if (isInputValid()) {
            seatNumber = Integer.parseInt(seat_number.getText());
            if(isSeatAvailable(flightNumber, seatNumber)){
            reserve(flightNumber, seatNumber);
            registerPassenger(first_name.getText(), last_name.getText(), email.getText(), phone_number.getText());
            flight.incrementReservationId();
            reservation_id.setText(String.valueOf(flight.getReservationId()));
            flight.incrementPassengerId();
            clearTextFields();
            message.setText("Reservation successful!");
            message.setTextFill(Color.GREEN);}
        } else {
            message.setText("Please fill in all fields");
            message.setTextFill(Color.RED);
        }
    }

    private boolean isInputValid() {
        return !isEmpty(first_name.getText()) &&
                !isEmpty(last_name.getText()) &&
                !isEmpty(email.getText()) &&
                !isEmpty(phone_number.getText()) &&
                !isEmpty(flight_number.getText()) &&
                isInteger(seat_number.getText());
    }

    private boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }


    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void registerPassenger (String fname, String lname, String email, String phone){
        flight.getPassengers().add (new Passenger(flight.getPassengerId(), fname, lname, email, phone));
    }
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("front.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("*************** Flight Reservation ********************");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user_page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("*************** Flight Reservation ********************");
        stage.setScene(scene);
        stage.show();
    }


    private void displayAvailableSeats(String flightNumber) {
        String flightInfo = "Available seats for Flight " + flightNumber+ ":\n\n";

        // Assuming you have a method to get the corresponding airplane for the flight
        Airplane airplane = getAirplaneForFlight(flightNumber);

        if (airplane != null) {
            boolean[] availableSeats = airplane.getSeats();
            boolean found = false;
            int count = 0;
            for (int i = 0; i < availableSeats.length; i++) {
                if (availableSeats[i]) {
                    flightInfo += (i + 1) + " | ";
                    found = true;
                    count++;
                    if (count % 5 == 0)
                        flightInfo += "\n";
                }
            }

            if (!found) {
                flightInfo += "\nNo Available Seat\n";
            }
        }
        available_seats.setText(flightInfo);
    }
    public Airplane getAirplaneForFlight(String flightNumber) {
        for (Flight flight : flight.getFlights()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.getAirplane();
            }
        }
        return null;
    }
    private void clearTextFields() {
        first_name.clear();
        last_name.clear();
        email.clear();
        phone_number.clear();
        flight_number.clear();
        seat_number.clear();
        available_seats.clear();
        message.setText("");  // Clear the message label
    }

}
