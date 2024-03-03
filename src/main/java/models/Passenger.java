package models;

public class Passenger extends Person{
    private int passengerId;

    public Passenger(int passengerId, String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
        this.passengerId = passengerId;
    }
    
    public int getPassengerId() {
        return passengerId;
    }

    public String getDetails() {
        return "Passenger: " + getPassengerId() + ", " +
               "Name: " + getName() + ", " +
               "Email: " + getEmail() + ", " +
               "Phone Number: " + getEmail();
    }

 
    
}
 