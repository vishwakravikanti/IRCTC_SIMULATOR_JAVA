import java.util.Random;

public class PNRGenerator {

    private static final Random random = new Random();

    public static String generatePNR() {
        long pnr = 1000000000L + random.nextInt(900000000);
        return String.valueOf(pnr);
    }
}