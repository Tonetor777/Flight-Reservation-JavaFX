package models;

public class Airplane {
    private String airplaneNumber;
    private boolean[] seats;

    public Airplane(String airplaneNumber, int totalSeats) {
        this.airplaneNumber = airplaneNumber;
        this.seats = new boolean[totalSeats];
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = true;
        }
    }

    public boolean[] getSeats() {
        return seats;
    }

    public String getAirplaneNumber() {
        return airplaneNumber;
    }

    public int getTotalSeats() {
        return seats.length;
    }

    public boolean reserveAirplaneSeat(int seat) {
        if (seat >= 0 && seat < seats.length && seats[seat]) {
            seats[seat-1] = false;
            return true;
        }
        return false;
    }


}
