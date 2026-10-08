import java.util.LinkedList;
import java.util.Queue;

public class WaitingList {

    private Queue<Passenger> racQueue;
    private Queue<Passenger> waitingQueue;

    private int maxRAC;
    private int maxWaiting;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public WaitingList(int maxRAC, int maxWaiting) {

        this.maxRAC = maxRAC;
        this.maxWaiting = maxWaiting;

        racQueue = new LinkedList<>();
        waitingQueue = new LinkedList<>();
    }


    // ==========================================
    // ADD PASSENGER TO RAC
    // ==========================================

    public boolean addToRAC(Passenger passenger) {

        if (racQueue.size() < maxRAC) {

            racQueue.add(passenger);

            return true;
        }

        return false;
    }


    // ==========================================
    // ADD PASSENGER TO WAITING LIST
    // ==========================================

    public boolean addToWaitingList(Passenger passenger) {

        if (waitingQueue.size() < maxWaiting) {

            waitingQueue.add(passenger);

            return true;
        }

        return false;
    }


    // ==========================================
    // REMOVE FIRST RAC PASSENGER
    // ==========================================

    public Passenger removeFromRAC() {

        return racQueue.poll();
    }


    // ==========================================
    // REMOVE FIRST WAITING PASSENGER
    // ==========================================

    public Passenger removeFromWaitingList() {

        return waitingQueue.poll();
    }


    // ==========================================
    // GET FIRST WAITING PASSENGER
    // ==========================================

    public Passenger peekWaitingPassenger() {

        return waitingQueue.peek();
    }


    // ==========================================
    // CHECK RAC AVAILABILITY
    // ==========================================

    public boolean hasRACPassenger() {

        return !racQueue.isEmpty();
    }


    // ==========================================
    // CHECK WAITING LIST AVAILABILITY
    // ==========================================

    public boolean hasWaitingPassenger() {

        return !waitingQueue.isEmpty();
    }


    // ==========================================
    // GET RAC COUNT
    // ==========================================

    public int getRACCount() {

        return racQueue.size();
    }


    // ==========================================
    // GET WAITING LIST COUNT
    // ==========================================

    public int getWaitingCount() {

        return waitingQueue.size();
    }


    // ==========================================
    // DISPLAY QUEUE STATUS
    // ==========================================

    public void displayStatus() {

        System.out.println("\n----- QUEUE STATUS -----");

        System.out.println(
                "RAC Passengers     : "
                        + racQueue.size()
        );

        System.out.println(
                "Waiting Passengers : "
                        + waitingQueue.size()
        );

        System.out.println("------------------------");
    }
}