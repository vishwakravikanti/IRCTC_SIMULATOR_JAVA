import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // CREATE TRAIN
        // ==========================================

        Train train = new Train(
                12701,
                "Hussain Sagar Express",
                "Hyderabad",
                "Mumbai",
                5
        );


        // ==========================================
        // CREATE BOOKING ENGINE
        // ==========================================

        BookingEngine engine = new BookingEngine(
                train,
                2,
                3
        );


        // ==========================================
        // START SWING GUI
        // ==========================================

        SwingUtilities.invokeLater(() -> {

            IRCTCSimulatorGUI gui =
                    new IRCTCSimulatorGUI(engine);

            gui.setVisible(true);
        });
    }
}