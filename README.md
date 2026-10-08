# IRCTC Tatkal Ticket Booking Simulator

A Java-based railway ticket booking simulator developed to demonstrate **Multithreading, Synchronization, Queue Management, Object-Oriented Programming, and GUI development using Java Swing**.

The project simulates a simplified IRCTC Tatkal booking system where multiple passengers can attempt to book seats. When confirmed seats are unavailable, passengers are moved to RAC and Waiting List queues.

---

## Project Overview

The IRCTC Tatkal Ticket Booking Simulator is designed as a small-scale railway reservation system.

The simulator supports:

- Passenger registration
- Train and seat management
- Ticket booking
- Confirmed ticket allocation
- RAC allocation
- Waiting List allocation
- Ticket cancellation
- RAC to Confirmed promotion
- Waiting List to RAC promotion
- PNR generation
- Ticket management
- Seat status monitoring
- Booking status monitoring
- Queue status monitoring
- Graphical User Interface using Java Swing
- Synchronized booking operations

The project demonstrates how **multithreading and synchronization** can be used to avoid conflicts when multiple passengers try to book limited seats simultaneously.

---

## Technologies Used

- **Programming Language:** Java
- **GUI:** Java Swing
- **Concepts:** 
  - Object-Oriented Programming
  - Multithreading
  - Synchronization
  - Collections
  - Queue
  - Encapsulation
  - Exception Handling
- **IDE:** VS Code / IntelliJ IDEA / Eclipse
- **Java Version:** JDK 17 or later recommended

---

## Project Structure

```text
IRCTC-Tatkal-Simulator/
│
├── data/
│
├── screenshots/
│
├── src/
│   ├── Booking.java
│   ├── BookingEngine.java
│   ├── IRCTCSimulatorGUI.java
│   ├── Main.java
│   ├── Passenger.java
│   ├── PNRGenerator.java
│   ├── Seat.java
│   ├── Ticket.java
│   ├── Train.java
│   └── WaitingList.java
│
└── README.md

Class Description
1. Train.java

Stores the basic information about a train.

Information includes:

Train Number
Train Name
Source
Destination
Total Seats
2. Seat.java

Represents an individual railway seat.

Each seat maintains:

Seat Number
Booking Status

The seat can be:

Available
Booked
3. Passenger.java

Stores passenger information.

Attributes include:

Passenger ID
Name
Age
Gender
4. Booking.java

Represents a passenger's booking.

It stores:

Booking ID
Passenger
Seat
Booking Status

Possible statuses include:

CONFIRMED
RAC
WAITING
CANCELLED
5. Ticket.java

Represents the generated railway ticket.

It contains:

PNR
Booking information
Train information
Passenger information
Seat information
Booking status
6. PNRGenerator.java

Generates a unique-looking numerical PNR for each booking.

Example:

PNR: 1256384917
7. WaitingList.java

Manages the RAC and Waiting List queues.

It uses Java's:

Queue
LinkedList

The system maintains separate queues for:

RAC passengers
Waiting List passengers
8. BookingEngine.java

This is the main business logic of the project.

It handles:

Ticket booking
Seat allocation
RAC allocation
Waiting List allocation
Cancellation
RAC promotion
Waiting List promotion
Ticket generation
Booking management
GUI output

The booking method is synchronized:

public synchronized Booking bookTicket(Passenger passenger)

This prevents multiple threads from booking the same seat simultaneously.

9. IRCTCSimulatorGUI.java

Provides the graphical user interface using Java Swing.

The GUI allows the user to:

Enter passenger details
Book tickets
Cancel bookings
View seats
View bookings
View queue status
View tickets
10. Main.java

The entry point of the application.

It:

Creates the train
Creates the booking engine
Initializes the Swing GUI
Starts the application
Booking Flow

The booking system follows this order:

Passenger
    |
    v
Available Seat?
    |
    +---- YES ----> CONFIRMED
    |
    NO
    |
    v
RAC Available?
    |
    +---- YES ----> RAC
    |
    NO
    |
    v
Waiting List Available?
    |
    +---- YES ----> WAITING
    |
    NO
    |
    v
Booking Failed
Cancellation and Promotion

When a confirmed ticket is cancelled:

Confirmed Seat
      |
      v
Cancellation
      |
      v
Seat Becomes Available
      |
      v
RAC Passenger
      |
      v
CONFIRMED
      |
      v
Waiting List Passenger
      |
      v
RAC

This demonstrates queue-based passenger promotion.

Multithreading

The project demonstrates the concept of concurrent ticket booking.

Multiple passengers can attempt to book tickets at the same time.

For example:

Passenger 1 ──┐
Passenger 2 ──┤
Passenger 3 ──┤──> Booking Engine
Passenger 4 ──┤
Passenger 5 ──┘

Since the booking method is synchronized, only one thread can modify the booking process at a time.

This prevents problems such as:

Double booking
Same seat assigned to multiple passengers
Incorrect seat availability
Inconsistent booking records
Synchronization

The booking engine uses Java's synchronized keyword.

Example:

public synchronized Booking bookTicket(
        Passenger passenger) {

Cancellation is also synchronized:

public synchronized boolean cancelBooking(
        int bookingId) {

This provides controlled access to shared booking resources.

GUI Features

The application provides a simple Swing-based interface.

Passenger Booking

The user can enter:

Passenger Name
Age
Gender

and click:

BOOK TICKET
Cancel Booking

The user can enter a Booking ID and cancel the booking.

Example:

Enter Booking ID: 1001
View Seats

Displays:

Seat 1 : Booked
Seat 2 : Available
Seat 3 : Booked
Seat 4 : Available
Seat 5 : Available
View Bookings

Displays booking information including:

Booking ID
Passenger
Seat
Status
View Queue

Displays:

RAC Passengers     : 2
Waiting Passengers : 3
View Tickets

Displays:

PNR
Train
Passenger
Seat
Status
Sample Train

The current simulator uses:

Train Number : 12701
Train Name   : Hussain Sagar Express
From         : Hyderabad
To           : Mumbai
Total Seats  : 5

The number of seats can be modified in Main.java.

How to Run
Step 1: Install Java

Install JDK 17 or later.

Verify the installation:

java -version

and:

javac -version
Step 2: Open the Project

Open the project folder:

IRCTC-Tatkal-Simulator

in your preferred Java IDE.

Step 3: Compile

Open the terminal inside the project folder and run:

javac src/*.java
Step 4: Run

Run the main class:

java -cp src Main
Example Booking Scenario

Suppose the train has 5 seats.

The first five passengers receive:

Passenger 1 → CONFIRMED
Passenger 2 → CONFIRMED
Passenger 3 → CONFIRMED
Passenger 4 → CONFIRMED
Passenger 5 → CONFIRMED

If the RAC limit is 2:

Passenger 6 → RAC
Passenger 7 → RAC

If the Waiting List limit is 3:

Passenger 8  → WAITING
Passenger 9  → WAITING
Passenger 10 → WAITING

If Passenger 1 cancels:

Passenger 6 → CONFIRMED
Passenger 8 → RAC

The system automatically promotes passengers according to the queue order.

Key Java Concepts Demonstrated
Object-Oriented Programming

The project uses:

Classes
Objects
Encapsulation
Constructors
Methods
Object relationships
Collections

The project uses:

ArrayList
Queue
LinkedList
Multithreading

Multiple booking requests can be processed concurrently.

Synchronization

Shared booking resources are protected using:

synchronized
Queue Management

RAC and Waiting List follow the FIFO (First In, First Out) principle.

Exception Handling

The GUI validates user input and handles invalid age and booking ID values.

Advantages
Simple and easy-to-use GUI
Demonstrates real-world railway booking logic
Prevents double booking using synchronization
Supports RAC and Waiting List
Automatic passenger promotion
Generates PNRs
Demonstrates Java OOP concepts
Demonstrates multithreading concepts
Suitable for academic demonstration
Limitations

This is an educational simulator and is not connected to the real IRCTC system.

Limitations include:

No real payment gateway
No real IRCTC API
No database integration
No real user authentication
No actual railway reservation
Simplified RAC implementation
PNR generation is for simulation purposes only
Future Enhancements

The project can be extended with:

MySQL database integration
User login and registration
Admin dashboard
Real-time train search
Multiple trains
Multiple coaches
Berth preferences
Payment simulation
Advanced PNR search
Booking history
PDF ticket generation
Email/SMS notification
Improved concurrent booking simulation
Project Objective

The main objective of this project is to demonstrate how Java can be used to develop a simplified railway reservation system while applying important programming concepts such as:

Object-Oriented Programming
        +
Collections
        +
Queues
        +
Multithreading
        +
Synchronization
        +
GUI Development
Author

Vishwak Ravikanti

B.Tech – CSE (AI & DS)

MNR University

Project Status
Status: Completed
Type: Academic Java Project
Interface: Java Swing GUI
Language: Java
License

This project is developed for educational and academic purposes.


