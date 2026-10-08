import javax.swing.*;
import java.awt.*;

public class IRCTCSimulatorGUI extends JFrame {

    private BookingEngine engine;

    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderBox;

    private JTextArea outputArea;

    private int passengerId = 111;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public IRCTCSimulatorGUI(BookingEngine engine) {

        this.engine = engine;

        setTitle("IRCTC Tatkal Ticket Booking Simulator");

        setSize(950, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();
    }


    // ==========================================
    // CREATE GUI
    // ==========================================

    private void createGUI() {

        JPanel mainPanel = new JPanel(
                new BorderLayout(15, 15)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );


        // ======================================
        // HEADER
        // ======================================

        JPanel headerPanel = new JPanel(
                new BorderLayout()
        );


        JLabel titleLabel = new JLabel(
                "IRCTC TATKAL TICKET BOOKING SIMULATOR",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );


        JLabel subtitleLabel = new JLabel(
                "Multithreading & Synchronization Based Railway Reservation System",
                SwingConstants.CENTER
        );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        headerPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        headerPanel.add(
                subtitleLabel,
                BorderLayout.SOUTH
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // ======================================
        // TRAIN INFORMATION PANEL
        // ======================================

        JPanel trainPanel = new JPanel();

        trainPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Train Information"
                )
        );

        trainPanel.setLayout(
                new GridLayout(
                        2,
                        4,
                        10,
                        5
                )
        );


        trainPanel.add(
                new JLabel(
                        "Train No: "
                                + engine.getTrain()
                                .getTrainNumber()
                )
        );


        trainPanel.add(
                new JLabel(
                        "Train: "
                                + engine.getTrain()
                                .getTrainName()
                )
        );


        trainPanel.add(
                new JLabel(
                        "From: "
                                + engine.getTrain()
                                .getSource()
                )
        );


        trainPanel.add(
                new JLabel(
                        "To: "
                                + engine.getTrain()
                                .getDestination()
                )
        );


        trainPanel.add(
                new JLabel(
                        "Total Seats: "
                                + engine.getTrain()
                                .getTotalSeats()
                )
        );


        trainPanel.add(
                new JLabel(
                        "System: Tatkal"
                )
        );


        trainPanel.add(
                new JLabel(
                        "Mode: Multithreading"
                )
        );


        trainPanel.add(
                new JLabel(
                        "Status: Online"
                )
        );


        // ======================================
        // CENTER PANEL
        // ======================================

        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );


        centerPanel.add(
                trainPanel,
                BorderLayout.NORTH
        );


        // ======================================
        // LEFT INPUT PANEL
        // ======================================

        JPanel inputPanel = new JPanel();


        inputPanel.setLayout(
                new GridLayout(
                        8,
                        1,
                        8,
                        8
                )
        );


        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Passenger Booking"
                )
        );


        inputPanel.add(
                new JLabel(
                        "Passenger Name"
                )
        );


        nameField = new JTextField();

        inputPanel.add(nameField);


        inputPanel.add(
                new JLabel(
                        "Age"
                )
        );


        ageField = new JTextField();

        inputPanel.add(ageField);


        inputPanel.add(
                new JLabel(
                        "Gender"
                )
        );


        genderBox = new JComboBox<>(
                new String[]{
                        "Male",
                        "Female",
                        "Other"
                }
        );


        inputPanel.add(genderBox);


        JButton bookButton =
                new JButton(
                        "BOOK TICKET"
                );


        JButton cancelButton =
                new JButton(
                        "CANCEL BOOKING"
                );


        inputPanel.add(bookButton);

        inputPanel.add(cancelButton);


        centerPanel.add(
                inputPanel,
                BorderLayout.WEST
        );


        // ======================================
        // OUTPUT AREA
        // ======================================

        outputArea = new JTextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);


        JScrollPane scrollPane =
                new JScrollPane(
                        outputArea
                );


        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "System Output"
                )
        );


        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // ======================================
        // BOTTOM BUTTON PANEL
        // ======================================

        JPanel buttonPanel = new JPanel(
                new GridLayout(
                        1,
                        4,
                        10,
                        10
                )
        );


        JButton seatsButton =
                new JButton(
                        "VIEW SEATS"
                );


        JButton bookingsButton =
                new JButton(
                        "VIEW BOOKINGS"
                );


        JButton queueButton =
                new JButton(
                        "VIEW QUEUE"
                );


        JButton ticketsButton =
                new JButton(
                        "VIEW TICKETS"
                );


        buttonPanel.add(
                seatsButton
        );


        buttonPanel.add(
                bookingsButton
        );


        buttonPanel.add(
                queueButton
        );


        buttonPanel.add(
                ticketsButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // ======================================
        // BUTTON ACTIONS
        // ======================================

        bookButton.addActionListener(
                e -> bookPassenger()
        );


        cancelButton.addActionListener(
                e -> cancelBooking()
        );


        seatsButton.addActionListener(
                e -> displaySeats()
        );


        bookingsButton.addActionListener(
                e -> displayBookings()
        );


        queueButton.addActionListener(
                e -> displayQueue()
        );


        ticketsButton.addActionListener(
                e -> displayTickets()
        );


        // ======================================
        // ADD MAIN PANEL
        // ======================================

        add(mainPanel);
    }


    // ==========================================
    // BOOK PASSENGER
    // ==========================================

    private void bookPassenger() {

        String name =
                nameField.getText().trim();


        String ageText =
                ageField.getText().trim();


        String gender =
                (String) genderBox.getSelectedItem();


        if (name.isEmpty()
                || ageText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter passenger details."
            );

            return;
        }


        try {

            int age =
                    Integer.parseInt(
                            ageText
                    );


            if (age <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be greater than zero."
                );

                return;
            }


            Passenger passenger =
                    new Passenger(
                            passengerId++,
                            name,
                            age,
                            gender
                    );


            Booking booking =
                    engine.bookTicket(
                            passenger
                    );


            if (booking != null) {

                outputArea.append(
                        "\n--------------------------------\n"
                );


                outputArea.append(
                        "PASSENGER: "
                                + name
                                + "\n"
                );


                outputArea.append(
                        "BOOKING ID: "
                                + booking.getBookingId()
                                + "\n"
                );


                outputArea.append(
                        "STATUS: "
                                + booking.getStatus()
                                + "\n"
                );


                if (booking.getSeat()
                        != null) {

                    outputArea.append(
                            "SEAT: "
                                    + booking
                                    .getSeat()
                                    .getSeatNumber()
                                    + "\n"
                    );

                } else {

                    outputArea.append(
                            "SEAT: Not Assigned\n"
                    );
                }


                outputArea.append(
                        "--------------------------------\n"
                );


            } else {

                outputArea.append(
                        "\nBooking failed for "
                                + name
                                + "\n"
                );
            }


            nameField.setText("");

            ageField.setText("");


        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid age."
            );
        }
    }


    // ==========================================
    // CANCEL BOOKING
    // ==========================================

    private void cancelBooking() {

        String bookingIdText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Booking ID:"
                );


        if (bookingIdText == null) {

            return;
        }


        try {

            int bookingId =
                    Integer.parseInt(
                            bookingIdText
                    );


            boolean result =
                    engine.cancelBooking(
                            bookingId
                    );


            if (result) {

                outputArea.append(
                        "\nBooking "
                                + bookingId
                                + " cancelled successfully.\n"
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Booking cancelled successfully."
                );


            } else {

                outputArea.append(
                        "\nUnable to cancel booking "
                                + bookingId
                                + ".\n"
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Booking could not be cancelled."
                );
            }


        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid booking ID."
            );
        }
    }


    // ==========================================
    // DISPLAY SEATS
    // ==========================================

    private void displaySeats() {

        outputArea.setText("");

        outputArea.append(
                "========== SEAT STATUS ==========\n\n"
        );


        engine.displaySeatsToGUI(
                outputArea
        );
    }


    // ==========================================
    // DISPLAY BOOKINGS
    // ==========================================

    private void displayBookings() {

        outputArea.setText("");

        outputArea.append(
                "========== BOOKINGS ==========\n\n"
        );


        engine.displayBookingsToGUI(
                outputArea
        );
    }


    // ==========================================
    // DISPLAY QUEUE
    // ==========================================

    private void displayQueue() {

        outputArea.setText("");

        outputArea.append(
                "========== QUEUE STATUS ==========\n\n"
        );


        engine.displayQueueStatusToGUI(
                outputArea
        );
    }


    // ==========================================
    // DISPLAY TICKETS
    // ==========================================

    private void displayTickets() {

        outputArea.setText("");

        outputArea.append(
                "========== TICKETS ==========\n\n"
        );


        engine.displayTicketsToGUI(
                outputArea
        );
    }
}