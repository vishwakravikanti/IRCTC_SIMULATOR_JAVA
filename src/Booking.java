public class Booking {

    private int bookingId;
    private Passenger passenger;
    private Seat seat;
    private String status;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public Booking(int bookingId, Passenger passenger,
                   Seat seat, String status) {

        this.bookingId = bookingId;
        this.passenger = passenger;
        this.seat = seat;
        this.status = status;
    }


    // ==========================================
    // GET BOOKING ID
    // ==========================================

    public int getBookingId() {

        return bookingId;
    }


    // ==========================================
    // GET PASSENGER
    // ==========================================

    public Passenger getPassenger() {

        return passenger;
    }


    // ==========================================
    // GET SEAT
    // ==========================================

    public Seat getSeat() {

        return seat;
    }


    // ==========================================
    // SET SEAT
    // ==========================================

    public void setSeat(Seat seat) {

        this.seat = seat;
    }


    // ==========================================
    // GET STATUS
    // ==========================================

    public String getStatus() {

        return status;
    }


    // ==========================================
    // SET STATUS
    // ==========================================

    public void setStatus(String status) {

        this.status = status;
    }


    // ==========================================
    // DISPLAY BOOKING
    // ==========================================

    public void displayBooking() {

        System.out.println(
                "Booking ID : " + bookingId
        );

        System.out.println(
                "Passenger  : "
                        + passenger.getName()
        );


        if (seat != null) {

            System.out.println(
                    "Seat       : "
                            + seat.getSeatNumber()
            );

        } else {

            System.out.println(
                    "Seat       : Not Assigned"
            );
        }


        System.out.println(
                "Status     : " + status
        );
    }
}