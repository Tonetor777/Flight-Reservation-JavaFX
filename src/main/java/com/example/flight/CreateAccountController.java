

package com.example.flight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import models.Account;

import java.io.IOException;

public class CreateAccountController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorLabel;


    private final AdminNavigationBar adminNavBar;

    public CreateAccountController() {
        this.adminNavBar = new AdminNavigationBar();
    }

    public CreateAccountController(AdminNavigationBar adminNavBar) {
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
    public void createAccount() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (validateInput(firstName, lastName, username, email, phoneNumber, password, confirmPassword)) {
            flight.getAccounts().add(new Account(firstName, lastName, username, email, phoneNumber, password));
            errorLabel.setText("Account Created!");
            errorLabel.setTextFill(Color.GREEN);
        }
    }

    private boolean validateInput(String firstName, String lastName, String username, String email, String phoneNumber,
                                  String password, String confirmPassword) {
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel.setText("All fields must be filled.");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match.");
            return false;
        }

        return true;
    }
}
