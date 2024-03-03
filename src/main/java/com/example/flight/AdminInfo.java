package com.example.flight;

public class AdminInfo {
    private static AdminInfo instance;

    private String firstName;
    private String lastName;
    private String userName;
    private String adminEmail;
    private String adminPhone;

    private AdminInfo() {
        // Private constructor to prevent instantiation.
    }

    public static AdminInfo getInstance() {
        if (instance == null) {
            instance = new AdminInfo();
        }
        return instance;
    }

    // Getters and setters for admin information...

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }
}

