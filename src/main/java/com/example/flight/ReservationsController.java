package com.example.flight;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Passenger;
import models.Reservation;

import java.io.IOException;

public class ReservationsController {

    @FXML
    private TableView<Reservation> reservationsTable;

    @FXML
    private TableColumn<Reservation, Integer> reservationIdColumn;

    @FXML
    private TableColumn<Reservation, String> flightNumberColumn;

    @FXML
    private TableColumn<Reservation, Integer> passengerIdColumn;

    @FXML
    private TableColumn<Reservation, Integer> seatNumberColumn;

    @FXML
    private TableColumn<Reservation, String> reservationDateColumn;

    @FXML
    private TextField filter_reservation;

    @FXML
    private TextField filter_flight_number;

    @FXML
    private TextField filter_passenger_id;

    @FXML
    private TextField filter_passenger;


    private final AdminNavigationBar adminNavBar;

    public ReservationsController() {
        this.adminNavBar = new AdminNavigationBar();
    }

    public ReservationsController(AdminNavigationBar adminNavBar) {
        this.adminNavBar = adminNavBar;
    }

    @FXML
    public void logOut(ActionEvent event) {
        try {
            adminNavBar.switchToHome(event);
        } catch (IOException e) {
            handleException("Error switching to home", e);
        }
    }

    @FXML
    public void goToProfile(ActionEvent event) {
        try {
            adminNavBar.goToProfile(event);
        } catch (IOException e) {
            handleException("Error navigating to Profile", e);
        }
    }
    @FXML
    public void goToAddFlight(ActionEvent event) {
        try {
            adminNavBar.goToAddFlight(event);
        } catch (IOException e) {
            handleException("Error navigating to Add Flight", e);
        }
    }

    @FXML
    public void goToReservations(ActionEvent event) {
        try {
            adminNavBar.goToReservations(event);
        } catch (IOException e) {
            handleException("Error navigating to Reservations", e);
        }
    }

    @FXML
    public void goToCreateAccount(ActionEvent event) {
        try {
            adminNavBar.goToCreateAccount(event);
        } catch (IOException e) {
            handleException("Error navigating to Create Account", e);
        }
    }

    private void handleException(String message, Exception exception) {
        // Customize this method based on your application's exception handling strategy
        exception.printStackTrace();
        // You may show an alert, log the exception, or perform other actions as needed
    }


    public void initialize() {

        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<Reservation , Integer>("reservationId"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Reservation , String>("flightNumber"));
        passengerIdColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("passengerId"));
        seatNumberColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("seatNumber"));
        reservationDateColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("reservationDateTime"));

        ObservableList<Reservation> reservationsObservableList = FXCollections.observableArrayList(flight.getReservations());
        FilteredList<Reservation> filteredReservations = new FilteredList<>(reservationsObservableList, b -> true);

        filter_reservation.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredReservations.setPredicate(reservation -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String search = newValue.toLowerCase();

                return String.valueOf(reservation.getReservationId()).toLowerCase().toLowerCase().contains(search) &&
                        (filter_reservation.getText().isEmpty() || String.valueOf(reservation.getReservationId()).toLowerCase().contains(filter_reservation.getText().toLowerCase()));
            });
        });

        filter_flight_number.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredReservations.setPredicate(reservation -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String search = newValue.toLowerCase();

                return reservation.getFlightNumber().toLowerCase().contains(search) &&
                        (filter_reservation.getText().isEmpty() ||reservation.getFlightNumber().toLowerCase().contains(filter_reservation.getText().toLowerCase()));
            });
        });

        filter_passenger_id.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredReservations.setPredicate(reservation -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String search = newValue.toLowerCase();

                return String.valueOf(reservation.getPassengerId()).toLowerCase().contains(search) &&
                        (filter_reservation.getText().isEmpty() || String.valueOf(reservation.getPassengerId()).toLowerCase().contains(filter_reservation.getText().toLowerCase()));
            });
        });

        reservationsTable.setItems(filteredReservations);
        initializePassengerTable();
    }


    @FXML
    private TableView<Passenger> passengerTable;

    @FXML
    private TableColumn<Passenger, Integer> passengerIdTableColumn;

    @FXML
    private TableColumn<Passenger, String> firstNameTableColumn;

    @FXML
    private TableColumn<Passenger, String> lastNameTableColumn;

    @FXML
    private TableColumn<Passenger, String> emailTableColumn;

    @FXML
    private TableColumn<Passenger, String> phoneNumberTableColumn;


    private void initializePassengerTable() {
        passengerIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("passengerId"));
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    public void passengerInformation() {
        ObservableList<Passenger> passengersData = FXCollections.observableArrayList();

        for (Passenger passenger : flight.getPassengers()) {
            if (String.valueOf(passenger.getPassengerId()).equals(filter_passenger.getText())) {
                passengersData.add(passenger);
            }
        }

        passengerTable.setItems(passengersData);
    }

}
