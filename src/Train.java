public class Train {

    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;

    public Train(int trainNumber, String trainName, String source,
                 String destination, int totalSeats) {

        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void displayTrain() {
        System.out.println("Train Number : " + trainNumber);
        System.out.println("Train Name   : " + trainName);
        System.out.println("From         : " + source);
        System.out.println("To           : " + destination);
        System.out.println("Total Seats  : " + totalSeats);
    }
}