import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private final List<Problem> problems = new ArrayList<>();

    public void addProblem(BufferedReader in) {
        String name = readNonEmptyLine(in, "Enter name: ");
        String category = readNonEmptyLine(in, "Enter category: ");
        String difficulty = readNonEmptyLine(in, "Enter difficulty: ");
        String date = readNonEmptyLine(in, "Enter date (YYYY-MM-DD): ");
        String status = readNonEmptyLine(in, "Enter status (solved/unsolved): ");

        problems.add(new Problem(name, category, difficulty, date, status));
        System.out.println("Added.");
    }

    public void viewAll() {
        if (problems.isEmpty()) {
            System.out.println("No problems saved yet.");
            return;
        }

        for (Problem p : problems) {
            System.out.println(p);
        }
    }

    public void filterProblems(BufferedReader in) {
        if (problems.isEmpty()) {
            System.out.println("No problems to filter.");
            return;
        }

        String key = readNonEmptyLine(in, "Enter category, difficulty or status to filter by: ");

        boolean found = false;
        for (Problem p : problems) {
            if (p.getCategory().equals(key) || p.getDifficulty().equals(key) || p.getStatus().equals(key)) {
                System.out.println(p);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matches found for: " + key);
        }
    }

    public void loadFromFile(String filename) {
        problems.clear();

        Path path = Path.of(filename);
        if (!Files.exists(path)) return;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Problem p = Problem.fromCsvLine(line);
                if (p != null) problems.add(p);
            }
        } catch (IOException e) {
            System.out.println("Could not load file: " + e.getMessage());
        }
    }

    public void saveToFile(String filename) {
        Path path = Path.of(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Problem p : problems) {
                writer.write(p.toCsvLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save file: " + e.getMessage());
        }
    }

    private static String readNonEmptyLine(BufferedReader in, String prompt) {
        while (true) {
            String line = readLine(in, prompt);
            if (line == null) {
                System.out.println("\nGoodbye!");
                System.exit(0);
            }
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) return trimmed;
            System.out.println("Please type something.");
        }
    }

    private static String readLine(BufferedReader in, String prompt) {
        try {
            System.out.print(prompt);
            return in.readLine();
        } catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
            return null;
        }
    }
}