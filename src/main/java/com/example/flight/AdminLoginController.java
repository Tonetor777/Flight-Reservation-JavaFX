package com.example.flight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import models.Account;

import java.io.IOException;
import java.util.Objects;

public class AdminLoginController {


    @FXML
    private Label admin_message;

    @FXML
    private TextField admin_username;

    @FXML
    private PasswordField admin_password;

    private Parent root;
    private AdminMainController adminMain;



    // Default constructor
    public AdminLoginController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_main.fxml"));
        try {
            Parent root = loader.load();
            adminMain = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Parameterized constructor
    public AdminLoginController(AdminMainController adminMain) {
        this.adminMain = adminMain;
    }

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("front.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Flight Reservation");
        stage.setScene(scene);
        stage.show();
    }

    public void login (ActionEvent event) throws IOException {
        String enteredUsername = admin_username.getText();
        String enteredPassword = admin_password.getText();
        String fname = "", lname= "", email = "", phone= "";
        // Replace this with your authentication logic
        if (isValidCredentials(enteredUsername, enteredPassword)) {
            for(Account admin: flight.getAccounts()){
                if (admin.getUsername().equals(enteredUsername)){
                     fname = admin.getFirstName();
                     lname = admin.getLastName();
                     email = admin.getEmail();
                     phone = admin.getPhoneNumber();
                }
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_main.fxml"));
            root = loader.load();

            AdminMainController adminMain = loader.getController();
            adminMain.setAdminInfo(fname, lname, enteredUsername, email, phone);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("**************** Admin Page ******************** ");
            stage.setScene(scene);
            stage.show();


        } else {
            admin_message.setText("Incorrect Username or Password");
        }
    }
    private boolean isValidCredentials(String enteredUsername, String enteredPassword) {
        // Example: Hardcoded admin credentials for demonstration purposes
        for (Account account : flight.getAccounts()){
            if (enteredUsername.equals(account.getUsername()) && enteredPassword.equals(account.getPassword())){
                return true;
            }
        }
        return false;
    }

    private void switchToAdminMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin_main.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("**************** Admin Page ******************** ");
        stage.setScene(scene);
        stage.show();
    }


}
