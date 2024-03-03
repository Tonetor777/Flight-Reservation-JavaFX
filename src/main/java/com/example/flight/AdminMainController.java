package com.example.flight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;
import java.util.Objects;

public class AdminMainController {

    @FXML
    private Label first_name;

    @FXML
    private Label last_name;

    @FXML
    private Label user_name;

    @FXML
    private Label email;

    @FXML
    private Label phone_number;

    @FXML
    private TextField currentPasswordTextField;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private TextField confirmNewPasswordTextField;


    @FXML
    private Label message;

    private String firstName;

    private String lastName;

    private String userName;

    private String adminEmail;

    private String adminPhone;
    private final AdminNavigationBar adminNavBar;

    public AdminMainController() {
        this.adminNavBar = new AdminNavigationBar();
    }

    public AdminMainController(AdminNavigationBar adminNavBar) {
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
    public void initialize() {
        displayAdminInfo();
    }

    public void setAdminInfo(String fname, String lname, String username, String phoneNumber , String emaill) {
        AdminInfo adminInfoSingleton = AdminInfo.getInstance();
        adminInfoSingleton.setFirstName(fname);
        adminInfoSingleton.setLastName(lname);
        adminInfoSingleton.setUserName(username);
        adminInfoSingleton.setAdminEmail(emaill);
        adminInfoSingleton.setAdminPhone(phoneNumber);

        displayAdminInfo();
    }

    public void displayAdminInfo (){
        AdminInfo adminInfoSingleton = AdminInfo.getInstance();
        first_name.setText(adminInfoSingleton.getFirstName());
        last_name.setText(adminInfoSingleton.getLastName());
        user_name.setText(adminInfoSingleton.getUserName());
        email.setText(adminInfoSingleton.getAdminEmail());
        phone_number.setText(adminInfoSingleton.getAdminPhone());
    }
    private void handleException(String message, Exception exception) {
        exception.printStackTrace();
    }


    public void changePassword(ActionEvent event) {
        String currentPassword = currentPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();
        String confirmNewPassword = confirmNewPasswordTextField.getText();

        if (isAdmin(currentPassword)!=null && isValidPassword(newPassword , confirmNewPassword)) {
            isAdmin(currentPassword).changePassword(newPassword);
            message.setText("Password changed successfully");
        } else {
            message.setText("Password change failed. Please check your inputs.");
        }
    }

    private Account isAdmin(String currentPassword) {
        for (Account admin : flight.getAccounts()){
            if (currentPassword.equals(admin.getPassword()))
                return admin;
        }
        return null;

    }
    private boolean isValidPassword (String newPassword, String confirmedPassword){
        if(newPassword.equals(confirmedPassword))
            return true;
        else return false;
    }
}
