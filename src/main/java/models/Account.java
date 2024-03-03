package models;

import java.util.*;

import com.example.flight.flight;
public class Account extends Person {

    private String username;
    private String password;

    public Account(String firstName, String lastName, String username, String email, String phoneNumber,
            String password) {
        super(firstName, lastName, email, phoneNumber);
        this.setPassword(password);
        this.setUserName(username);

    }


    public Account() {
        super();
    }

    @Override
    public String getDetails() {
        return "Name: " + getName() + ", " +
                "Email: " + getEmail() + ", " +
                "Phone Number: " + getPhoneNumber();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Your password has been changed successfully\n");
    }

    public static void createAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter First Name: ");
        String firstName = input.next();

        System.out.println("Enter Last Name: ");
        String lastName = input.next();

        System.out.println("Enter Username: ");
        String username = input.next();

        System.out.println("Enter Email: ");
        String email = input.next();

        System.out.println("Enter Phone Number: ");
        String phoneNumber = input.next();

        System.out.println("Enter Password: ");
        String password = input.next();

        flight.getAccounts().add(new Account(firstName, lastName, username, email, phoneNumber, password));
    }

    public void addFlight() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Flight Number: ");
        String flightNumber = input.next();
        System.out.println("Enter Departure Location: ");
        String departureLocation = input.next();
        System.out.println("Enter Destination");
        String destination = input.next();
        System.out.println("Enter Departure Date (DD/MM/YYYY): ");
        String date = input.next();
        System.out.println("Enter Departure Time(\"HH:mm:ss\"): ");
        String departureTime = input.next();
        System.out.println("Enter Arrival Time(\"HH:mm:ss\"): ");
        String arrivalTime = input.next();
        System.out.println("Enter Airplane Number: ");
        String airplaneNumber = input.next();
        System.out.println("Enter Total Seats: ");
        int totalSeats = input.nextInt();
        System.out.println("Enter Price (Per Person): ");
        double price = input.nextDouble();
        Airplane airplane = new Airplane(airplaneNumber, totalSeats);
        flight.getFlights().add(new Flight(flightNumber, departureLocation, destination, date, departureTime, arrivalTime, airplane, price));

    }

    public void displayPassengers() {
        System.out.println("\n\tPassengers List: \n");
        for (Passenger passenger : flight.getPassengers()) {
            System.out.println(passenger.getDetails());
        }
        System.out.println("\n");
    }

    public void reservationList() {
        System.out.println("\n\tReservation: \n");
        for (Reservation reservation : flight.getReservations()) {
            System.out.println(reservation.getReservationDetails());
        }
        System.out.println("\n");
    }
}
