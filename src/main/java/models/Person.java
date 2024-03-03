package models;

abstract class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Person(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email; 
        this.phoneNumber = phoneNumber;
    }
    public Person(){}


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }


    public void setfname(String fname) {
        this.firstName = fname;
    }

    public void setlname(String lname) {
        this.lastName = lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public abstract String getDetails();
}