package com.example.flight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Account;
import models.Flight;
import models.Passenger;
import models.Reservation;
import models.Airplane;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class flight extends Application {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Flight> flights = new ArrayList<>();
    private static ArrayList<Passenger> passengers = new ArrayList<>();

    private static ArrayList<Airplane> airplanes= new ArrayList<>();
    private static ArrayList<Reservation> reservations= new ArrayList<>();

     private static int passengerId = 100;

    private static int reservationID = 1000;

    public static int getReservationId () {return reservationID;};
    public static int getPassengerId () {return passengerId;};

    public static void incrementPassengerId () {passengerId++;}

    public static void incrementReservationId () {reservationID++;}
    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public static ArrayList<Airplane> getAirplanes() {
        return airplanes;
    }
     @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(flight.class.getResource("front.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800 , Color.rgb(0, 0 , 0));
        stage.setTitle("Flight Reservation");
        stage.setScene(scene);
        stage.show();
    }

    public static void generateReport() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("flight_report.txt"))) {
            // Report header
            writer.println("Flight Management System Report");
            writer.println("================================\n");

            writer.println("Available Flights:");
            for (Flight flight : flights) {
                writer.println(flight.getFlightDetails());
            }
            writer.println();

            // Display Passenger List
            writer.println("Passenger List:");
            for (Passenger passenger : passengers) {
                writer.println(passenger.getDetails());
            }
            writer.println();

            writer.println("Reservations:");
            for (Reservation reservation : reservations) {
                writer.println(reservation.getReservationDetails());
            }

            System.out.println("Report generated successfully. Check 'flight_report.txt'.");
        } catch (IOException e) {
            System.out.println("Error while generating the report.");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        accounts.add(new Account("Robel", "Sisay", "Admin", "robelsisay45@gmail.com", "0930856496", "adminaccount"));
        Airplane airplane1 = new Airplane("CF83RT" , 100);
        Airplane airplane2 = new Airplane("GY76UI" , 120);
        Airplane airplane3 = new Airplane("N278YU" , 90);
        Airplane airplane4 = new Airplane("CY98AS" , 100);
        Airplane airplane5 = new Airplane("D465OH" , 110);
        Airplane airplane6 = new Airplane("AB58JK" , 100);
        Airplane airplane7 = new Airplane("ZD45GH" , 100);
        Airplane airplane8= new Airplane("DR45YH" , 100);
        Airplane airplane9 = new Airplane("OK86GF" , 100);
        Airplane airplane10 = new Airplane("ER45GB" , 110);
        flights.add(new Flight("E145", "Addis Ababa", "Hawassa", "22/01/2024", "7:30 pm", "9:00 pm", airplane1, 1000));
        flights.add(new Flight("E146", "Addis Ababa", "BahirDar", "22/01/2024", "8:30 pm", "10:00 pm", airplane2, 2000));
        flights.add(new Flight("H567", "Hawassa", "BahirDar", "22/01/2024", "8:30 pm", "10:00 pm", airplane3, 4000));
        flights.add(new Flight("H345", "Hawassa", "Addis Ababa", "23/01/2024", "4:30 am", "5:30 am", airplane4, 3000));
        flights.add(new Flight("B675", "BahirDar", "Addis Ababa", "23/01/2024", "8:30 pm", "10:00 pm", airplane5, 2000));
        flights.add(new Flight("A749", "Addis Ababa", "Hawassa", "23/01/2024", "7:30 pm", "9:00 pm", airplane6, 4000));
        flights.add(new Flight("A586", "Addis Ababa", "Gondar", "24/01/2024", "6:30 pm", "8:00 pm", airplane7, 5000));
        flights.add(new Flight("H567", "Gondar", "BahirDar", "25/01/2024", "1:30 pm", "3:00 pm", airplane8, 1000));
        flights.add(new Flight("H787", "BahirDar", "Gondar", "25/01/2024", "8:30 pm", "10:00 pm", airplane9, 1000));
        flights.add(new Flight("H234", "Addis Ababa", "Gondar", "26/01/2024", "5:30 pm", "7:00 pm", airplane10, 3000));
        passengers.add(new Passenger(45, "Abebe" , "Kebede" , "abebe@gmail.com", "0912345678"));
        passengers.add(new Passenger(80, "Dawit" , "solomon" , "davesolomon@gmail.com", "0967547890"));
        passengers.add(new Passenger(88, "Hana" , "Dawit" , "hannicho@gmail.com", "0998745678"));
        passengers.add(new Passenger(90, "Robel" , "Sisay" , "robelsisay@gmail.com", "0930856496"));
        passengers.add(new Passenger(92, "Olimars" , "Teshome" , "olimars@gmail.com", "0987654567"));
        passengers.add(new Passenger(97, "Meron" , "Sisay" , "mersonsisay@gmail.com", "0935465778"));
        passengers.add(new Passenger(99, "Saron" , "Teshome" , "sariteshe@gmail.com", "0910293847"));
        reservations.add(new Reservation(900, "E145" , 45, 1));
        reservations.add(new Reservation(923, "E145" , 80, 4));
        reservations.add(new Reservation(940, "B675" , 88, 5));
        reservations.add(new Reservation(956, "H567" , 90, 16));
        reservations.add(new Reservation(978, "A794" , 92, 55));
        reservations.add(new Reservation(980, "A567" , 97, 78));
        reservations.add(new Reservation(912, "H234" , 99, 65));
        launch();

    }
}