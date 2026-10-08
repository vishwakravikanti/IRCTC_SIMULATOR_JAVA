import java.util.ArrayList;
import java.util.List;

public class BookingEngine {

    private Train train;

    private List<Seat> seats;
    private List<Booking> bookings;
    private List<Ticket> tickets;

    private WaitingList waitingList;

    private int bookingIdCounter = 1001;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public BookingEngine(
            Train train,
            int maxRAC,
            int maxWaiting) {

        this.train = train;

        seats = new ArrayList<>();
        bookings = new ArrayList<>();
        tickets = new ArrayList<>();

        waitingList =
                new WaitingList(
                        maxRAC,
                        maxWaiting
                );


        // Create seats
        for (int i = 1;
             i <= train.getTotalSeats();
             i++) {

            seats.add(
                    new Seat(i)
            );
        }
    }


    // ==========================================
    // GET TRAIN
    // ==========================================

    public Train getTrain() {

        return train;
    }


    // ==========================================
    // BOOK TICKET
    // ==========================================

    public synchronized Booking bookTicket(
            Passenger passenger) {

        // --------------------------------------
        // STEP 1: CONFIRMED SEAT
        // --------------------------------------

        for (Seat seat : seats) {

            if (!seat.isBooked()) {

                seat.bookSeat();

                Booking booking =
                        new Booking(
                                bookingIdCounter++,
                                passenger,
                                seat,
                                "CONFIRMED"
                        );

                bookings.add(booking);

                String pnr =
                        PNRGenerator.generatePNR();

                Ticket ticket =
                        new Ticket(
                                pnr,
                                booking,
                                train
                        );

                tickets.add(ticket);

                return booking;
            }
        }


        // --------------------------------------
        // STEP 2: RAC
        // --------------------------------------

        if (waitingList.addToRAC(passenger)) {

            Booking booking =
                    new Booking(
                            bookingIdCounter++,
                            passenger,
                            null,
                            "RAC"
                    );

            bookings.add(booking);

            String pnr =
                    PNRGenerator.generatePNR();

            Ticket ticket =
                    new Ticket(
                            pnr,
                            booking,
                            train
                    );

            tickets.add(ticket);

            return booking;
        }


        // --------------------------------------
        // STEP 3: WAITING LIST
        // --------------------------------------

        if (waitingList.addToWaitingList(passenger)) {

            Booking booking =
                    new Booking(
                            bookingIdCounter++,
                            passenger,
                            null,
                            "WAITING"
                    );

            bookings.add(booking);

            String pnr =
                    PNRGenerator.generatePNR();

            Ticket ticket =
                    new Ticket(
                            pnr,
                            booking,
                            train
                    );

            tickets.add(ticket);

            return booking;
        }


        // --------------------------------------
        // STEP 4: FULL
        // --------------------------------------

        return null;
    }


    // ==========================================
    // CANCEL BOOKING
    // ==========================================

    public synchronized boolean cancelBooking(
            int bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId()
                    == bookingId) {

                if (!booking.getStatus()
                        .equals("CONFIRMED")) {

                    return false;
                }


                // Free the seat
                Seat seat =
                        booking.getSeat();

                if (seat != null) {

                    seat.cancelSeat();
                }


                // Mark booking cancelled
                booking.setStatus(
                        "CANCELLED"
                );


                // Promote RAC passenger
                promoteRAC();

                return true;
            }
        }

        return false;
    }


    // ==========================================
    // PROMOTE RAC
    // ==========================================

    private void promoteRAC() {

        if (!waitingList.hasRACPassenger()) {

            return;
        }


        Passenger passenger =
                waitingList.removeFromRAC();


        // Find available seat
        for (Seat seat : seats) {

            if (!seat.isBooked()) {

                seat.bookSeat();


                // Find passenger's RAC booking
                for (Booking booking : bookings) {

                    if (booking.getPassenger()
                            == passenger
                            &&
                            booking.getStatus()
                                    .equals("RAC")) {

                        booking.setSeat(seat);

                        booking.setStatus(
                                "CONFIRMED"
                        );

                        break;
                    }
                }


                // Promote waiting passenger
                promoteWaitingToRAC();

                return;
            }
        }
    }


    // ==========================================
    // PROMOTE WAITING → RAC
    // ==========================================

    private void promoteWaitingToRAC() {

        if (!waitingList.hasWaitingPassenger()) {

            return;
        }


        Passenger passenger =
                waitingList.removeFromWaitingList();


        // Find passenger's booking
        for (Booking booking : bookings) {

            if (booking.getPassenger()
                    == passenger
                    &&
                    booking.getStatus()
                            .equals("WAITING")) {

                booking.setStatus(
                        "RAC"
                );

                break;
            }
        }
    }


    // ==========================================
    // DISPLAY SEATS
    // ==========================================

    public void displaySeats() {

        System.out.println(
                "\n========== SEAT STATUS =========="
        );

        for (Seat seat : seats) {

            seat.displaySeat();
        }
    }


    // ==========================================
    // DISPLAY BOOKINGS
    // ==========================================

    public void displayBookings() {

        System.out.println(
                "\n========== BOOKINGS =========="
        );

        if (bookings.isEmpty()) {

            System.out.println(
                    "No bookings available."
            );

            return;
        }


        for (Booking booking : bookings) {

            booking.displayBooking();

            System.out.println(
                    "-----------------------------"
            );
        }
    }


    // ==========================================
    // DISPLAY QUEUE STATUS
    // ==========================================

    public void displayQueueStatus() {

        waitingList.displayStatus();
    }


    // ==========================================
    // DISPLAY TICKETS
    // ==========================================

    public void displayTickets() {

        System.out.println(
                "\n========== TICKETS =========="
        );

        if (tickets.isEmpty()) {

            System.out.println(
                    "No tickets available."
            );

            return;
        }


        for (Ticket ticket : tickets) {

            ticket.displayTicket();
        }
    }


    // ==========================================
    // GET BOOKINGS
    // ==========================================

    public List<Booking> getBookings() {

        return bookings;
    }


    // ==========================================
    // GET TICKETS
    // ==========================================

    public List<Ticket> getTickets() {

        return tickets;
    }


    // ==========================================
    // GUI - DISPLAY SEATS
    // ==========================================

    public void displaySeatsToGUI(
            javax.swing.JTextArea outputArea) {

        for (Seat seat : seats) {

            String status =
                    seat.isBooked()
                            ? "Booked"
                            : "Available";

            outputArea.append(
                    "Seat "
                            + seat.getSeatNumber()
                            + " : "
                            + status
                            + "\n"
            );
        }
    }


    // ==========================================
    // GUI - DISPLAY BOOKINGS
    // ==========================================

    public void displayBookingsToGUI(
            javax.swing.JTextArea outputArea) {

        if (bookings.isEmpty()) {

            outputArea.append(
                    "No bookings available.\n"
            );

            return;
        }


        for (Booking booking : bookings) {

            outputArea.append(
                    "Booking ID : "
                            + booking.getBookingId()
                            + "\n"
            );


            outputArea.append(
                    "Passenger  : "
                            + booking.getPassenger()
                            .getName()
                            + "\n"
            );


            if (booking.getSeat() != null) {

                outputArea.append(
                        "Seat       : "
                                + booking.getSeat()
                                .getSeatNumber()
                                + "\n"
                );

            } else {

                outputArea.append(
                        "Seat       : Not Assigned\n"
                );
            }


            outputArea.append(
                    "Status     : "
                            + booking.getStatus()
                            + "\n"
            );


            outputArea.append(
                    "-----------------------------\n"
            );
        }
    }


    // ==========================================
    // GUI - DISPLAY QUEUE
    // ==========================================

    public void displayQueueStatusToGUI(
            javax.swing.JTextArea outputArea) {

        outputArea.append(
                "RAC Passengers     : "
                        + waitingList.getRACCount()
                        + "\n"
        );


        outputArea.append(
                "Waiting Passengers : "
                        + waitingList.getWaitingCount()
                        + "\n"
        );
    }


    // ==========================================
    // GUI - DISPLAY TICKETS
    // ==========================================

    public void displayTicketsToGUI(
            javax.swing.JTextArea outputArea) {

        if (tickets.isEmpty()) {

            outputArea.append(
                    "No tickets available.\n"
            );

            return;
        }


        for (Ticket ticket : tickets) {

            Booking booking =
                    ticket.getBooking();


            outputArea.append(
                    "PNR          : "
                            + ticket.getPnr()
                            + "\n"
            );


            outputArea.append(
                    "Train        : "
                            + ticket.getTrain()
                            .getTrainName()
                            + "\n"
            );


            outputArea.append(
                    "Passenger    : "
                            + booking.getPassenger()
                            .getName()
                            + "\n"
            );


            if (booking.getSeat() != null) {

                outputArea.append(
                        "Seat         : "
                                + booking.getSeat()
                                .getSeatNumber()
                                + "\n"
                );

            } else {

                outputArea.append(
                        "Seat         : Not Assigned\n"
                );
            }


            outputArea.append(
                    "Status       : "
                            + booking.getStatus()
                            + "\n"
            );


            outputArea.append(
                    "-----------------------------\n"
            );
        }
    }
}