package models;

import java.util.*;

public class Reservation {

    private String flightNumber;

    private int passengerId;
    private int reservationId;
    private int seatNumber;
    private Date reservationDateTime;

    // Constructor
    public Reservation(int reservationId, String flightNumber, int passengerId, int seatNumber) {
        this.reservationId = reservationId;
        this.seatNumber = seatNumber;
        this.reservationDateTime = new Date();
        this.flightNumber = flightNumber;
        this.passengerId = passengerId;
    }

    public Date getReservationDateTime() {
        return reservationDateTime;
    }
    public int getReservationId(){
        return reservationId;
    }
    public int getSeatNumber(){
        return seatNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getPassengerId() {
        return passengerId;
    }
    public String getReservationDetails() {
        return "Reservation " + reservationId + " for seat " + seatNumber + " at " + reservationDateTime;
    }
}

