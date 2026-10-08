public class Seat {

    private int seatNumber;
    private boolean booked;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void bookSeat() {
        booked = true;
    }

    public void cancelSeat() {
        booked = false;
    }

    public void displaySeat() {
        String status = booked ? "Booked" : "Available";

        System.out.println(
            "Seat " + seatNumber + " : " + status
        );
    }
}