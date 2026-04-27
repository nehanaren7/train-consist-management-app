import java.util.regex.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("UC11 - Validate Train ID & Cargo Code");
        System.out.println("======================================\n");

        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        System.out.println("Train ID: " + trainId + " -> " + (trainMatcher.matches() ? "Valid" : "Invalid"));
        System.out.println("Cargo Code: " + cargoCode + " -> " + (cargoMatcher.matches() ? "Valid" : "Invalid"));
    }
}