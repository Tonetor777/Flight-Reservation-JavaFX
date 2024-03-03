package models;

public class Flight {
    private String flightNumber;
    private String departureLocation;
    private String destination;

    private String date;
    private String departureDateTime;
    private String arrivalDateTime;
    private Airplane airplane;

    double price;

    public Flight(String flightNumber, String departureLocation, String destination, String date, String departureDateTime, String arrivalTime, Airplane airplane , double price) {
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.date = date;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalTime;
        this.airplane = airplane;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureDateTime;
    }

    public String getArrivalTime() {
        return arrivalDateTime;
    }

    public int getTotalSeats() {
        return airplane.getTotalSeats();
    }

    public String getDate(){return date;}

    public double getPrice(){return price;}

    
    public String getFlightDetails() {
        return "Flight " + flightNumber + " from " + departureLocation + " to " + destination + ", Departure Time "
                + departureDateTime + ", Estimated arrival time " + arrivalDateTime;
    }

    public void reserveSeat(int seat) {
        airplane.reserveAirplaneSeat(seat);
    }

    public Airplane getAirplane() {
        return airplane;
    }
}