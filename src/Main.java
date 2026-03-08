import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final String DATA_FILE = "problems.csv";

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.loadFromFile(DATA_FILE);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int choice;
        do {
            System.out.println("\n--- Interview Prep Tracker ---");
            System.out.println("1. Add problem");
            System.out.println("2. View all");
            System.out.println("3. Filter");
            System.out.println("4. Save & Exit");
            choice = readInt(in, "Choice: ");

            switch (choice) {
                case 1 -> tracker.addProblem(in);
                case 2 -> tracker.viewAll();
                case 3 -> tracker.filterProblems(in);
                case 4 -> {
                    tracker.saveToFile(DATA_FILE);
                    System.out.println("Saved. Exiting...");
                }
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static int readInt(BufferedReader in, String prompt) {
        while (true) {
            String line = readLine(in, prompt);
            if (line == null) {
                System.out.println("\nGoodbye!");
                System.exit(0);
            }

            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("That is not a number. Try again.");
            }
        }
    }

    private static String readLine(BufferedReader in, String prompt) {
        try {
            System.out.print(prompt);
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
    }
}
