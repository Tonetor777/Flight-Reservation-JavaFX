package com.example.flight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Airplane;
import models.Flight;

import java.io.IOException;
import java.util.Objects;

public class AddFlightController {

    @FXML
    private TextField flight_number;

    @FXML
    private TextField departure_location;

    @FXML
    private TextField destination;

    @FXML
    private DatePicker date;

    @FXML
    private TextField departure_time;


    @FXML
    private TextField arrival_time;

    @FXML
    private TextField airplane_number;

    @FXML
    private TextField total_seats;

    @FXML
    private TextField price;


    @FXML
    private Label message;


    private final AdminNavigationBar adminNavBar;

    public AddFlightController() {
        this.adminNavBar = new AdminNavigationBar();
    }

    public AddFlightController(AdminNavigationBar adminNavBar) {
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

    @FXML
    public void goToProfile(ActionEvent event) {
        try {
            adminNavBar.goToProfile(event);
        } catch (IOException e) {
            handleException("Error navigating to Profile", e);
        }
    }

    private void handleException(String message, Exception exception) {
        // Customize this method based on your application's exception handling strategy
        exception.printStackTrace();
        // You may show an alert, log the exception, or perform other actions as needed
    }


    private boolean validateFields() {
        return !flight_number.getText().isEmpty() &&
                !departure_location.getText().isEmpty() &&
                !destination.getText().isEmpty() &&
                date.getValue() != null &&
                !departure_time.getText().isEmpty() &&
                !arrival_time.getText().isEmpty() &&
                !airplane_number.getText().isEmpty() &&
                !total_seats.getText().isEmpty() &&
                !price.getText().isEmpty();
    }


    public void addFlightInformation() {
        if(validateFields()) {
            String flightNumber = flight_number.getText();
            String departureLocation = departure_location.getText();
            String destination = this.destination.getText();
            String dateString = date.getValue().toString();
            String departureTime = departure_time.getText();
            String arrivalTime = arrival_time.getText();
            String airplaneNumber = airplane_number.getText();
            int totalSeats = Integer.parseInt(total_seats.getText());
            double price = Double.parseDouble(this.price.getText());
            Airplane airplane = new Airplane(airplaneNumber, totalSeats);
            flight.getAirplanes().add(airplane);
            flight.getFlights().add(new Flight(flightNumber, departureLocation, destination, dateString, departureTime, arrivalTime, airplane, price));
            clearFields();
            message.setText("Flight added successfully!");
            message.setTextFill(Color.GREEN);
        }
        else {
            message.setText("Please fill in all fields");
            message.setTextFill(Color.RED);
        }
    }
    public void clearFields() {
        if (!flight_number.getText().isEmpty()){flight_number.clear();}
        if (!departure_location.getText().isEmpty()){departure_location.clear();}
        if (!destination.getText().isEmpty()){destination.clear();}
        if (date.getValue() != null){date.getEditor().clear();date.setValue(null); }
        if (!departure_time.getText().isEmpty()){departure_time.clear();}
        if (!arrival_time.getText().isEmpty()){arrival_time.clear();}
        if (!airplane_number.getText().isEmpty()){airplane_number.clear();}
        if (!total_seats.getText().isEmpty()){total_seats.clear();}
        if (!price.getText().isEmpty()){price.clear();}
    }
}

