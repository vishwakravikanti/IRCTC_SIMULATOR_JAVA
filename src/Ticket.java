public class Ticket {

    private String pnr;
    private Booking booking;
    private Train train;

    public Ticket(String pnr, Booking booking, Train train) {
        this.pnr = pnr;
        this.booking = booking;
        this.train = train;
    }

    public String getPnr() {
        return pnr;
    }

    public Booking getBooking() {
        return booking;
    }

    public Train getTrain() {
        return train;
    }

    public void displayTicket() {

        System.out.println("\n========== TICKET ==========");
        System.out.println("PNR          : " + pnr);
        System.out.println("Train No.    : " + train.getTrainNumber());
        System.out.println("Train Name   : " + train.getTrainName());
        System.out.println("From         : " + train.getSource());
        System.out.println("To           : " + train.getDestination());
        System.out.println("Passenger    : " + booking.getPassenger().getName());

        if (booking.getSeat() != null) {
            System.out.println("Seat No.     : " + booking.getSeat().getSeatNumber());
        } else {
            System.out.println("Seat No.     : Not Assigned");
        }

        System.out.println("Status       : " + booking.getStatus());
        System.out.println("============================\n");
    }
}